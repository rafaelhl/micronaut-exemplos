package clubes.repository;

import clubes.domain.Clube;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface ClubesRepository {
	Single<Clube> save(Clube clube);
	Maybe<Clube> findClube(Long id);
	Flowable<Clube> findAll();
	Single<Long> count();
}
