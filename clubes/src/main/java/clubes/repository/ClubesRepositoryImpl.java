package clubes.repository;

import clubes.domain.Clube;
import io.micronaut.spring.tx.annotation.Transactional;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class ClubesRepositoryImpl implements ClubesRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Single<Clube> save(Clube clube) {
		this.entityManager.persist(clube);
		return Single.just(clube);
	}

	@Override
	@Transactional(readOnly = true)
	public Maybe<Clube> findClube(Long id) {
		return Maybe.just(this.entityManager.find(Clube.class, id));
	}

	@Override
	@Transactional(readOnly = true)
	public Flowable<Clube> findAll() {
		return Flowable.fromIterable(this.entityManager.createQuery("FROM Clube", Clube.class).getResultList());
	}

	@Override
	@Transactional(readOnly = true)
	public Single<Long> count() {
		return Single.just(this.entityManager.createQuery("SELECT count(id) FROM Clube", Long.class).getSingleResult());
	}

}