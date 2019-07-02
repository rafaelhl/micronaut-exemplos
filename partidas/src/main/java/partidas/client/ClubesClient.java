package partidas.client;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

@CircuitBreaker
@Client("clubes")
public interface ClubesClient extends ClubesApi {
}