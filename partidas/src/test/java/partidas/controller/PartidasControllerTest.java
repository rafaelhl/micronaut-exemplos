package partidas.controller;

import java.util.Date;
import javax.inject.Inject;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Single;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import partidas.domain.Partida;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class PartidasControllerTest {

    @Inject
    PartidasClient partidasClient;

    @Test
    void testSalvarPartida() {
        Partida partida = this.partidasClient.salvarPartida(new Partida(1L, 2L, Short.valueOf("2"), Short.valueOf("1"), new Date())).blockingGet();
        Assertions.assertNotNull(partida);
    }

    @Client("/partidas")
    interface PartidasClient {
        @Post
        Single<Partida> salvarPartida(Partida partida);
    }

}
