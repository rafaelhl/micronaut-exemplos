package clubes.client;

import clubes.api.ClubesApi;
import io.micronaut.http.client.annotation.Client;

@Client("/clubes")
public interface ClubesClient extends ClubesApi {
}