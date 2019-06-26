package clubes.api;

import clubes.domain.Clube;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface ClubesApi {
	@Get
	Flowable<Clube> index();
	@Get("/{identificador}")
	Maybe<Clube> show(Long identificador);
}
