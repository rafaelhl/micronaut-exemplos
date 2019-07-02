package partidas.repository;

import javax.validation.constraints.NotNull;

import io.reactivex.Flowable;
import io.reactivex.Single;
import partidas.domain.Partida;

public interface PartidasRepository {
	Single<Partida> save(@NotNull Partida partida);
	Flowable<Partida> findAll();
	Single<Long> count();
}
