package partidas.client;

import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface ClubesApi {
	@Get("/clubes/{identificador}")
	Maybe<Clube> show(Long identificador);
}
