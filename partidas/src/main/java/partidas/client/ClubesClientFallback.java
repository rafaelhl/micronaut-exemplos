package partidas.client;

import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Maybe;

@Fallback
public class ClubesClientFallback implements ClubesApi {
	@Override
	public Maybe<Clube> show(Long identificador) {
		if (identificador == 1L) {
			return Maybe.just(new Clube("America de Natal", "Arena das Dunas"));
		}
		if (identificador == 2L) {
			return Maybe.just(new Clube("ABC FC", "Fraqueirão"));
		}
		return Maybe.error(new IllegalArgumentException("Erro tentando consulta clube..."));
	}
}
