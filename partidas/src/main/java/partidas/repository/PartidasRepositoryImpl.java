package partidas.repository;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import partidas.domain.Partida;

@Singleton
@AllArgsConstructor
public class PartidasRepositoryImpl implements PartidasRepository {

	private MongoClient mongoClient;

	@Override
	public Single<Partida> save(@NotNull Partida partida) {
		return Single.fromPublisher(getCollection().insertOne(partida)).map(success -> partida);
	}

	@Override
	public Flowable<Partida> findAll() {
		return Flowable.fromPublisher(getCollection().find());
	}

	@Override
	public Single<Long> count() {
		return Single.fromPublisher(getCollection().countDocuments());
	}

	private MongoCollection<Partida> getCollection() {
		return this.mongoClient.getDatabase("partidasDB").getCollection("partidas", Partida.class);
	}
}
