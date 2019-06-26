package clubes.controller;

import clubes.client.ClubesClient;
import clubes.domain.Clube;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class ClubesControllerTest {

    @Inject
    ClubesClient clubeClient;

    @Test
    void testIndex() {
        Clube clube = this.clubeClient.index().blockingLast();
        Assertions.assertEquals("ABC FC", clube.getNome());
        Assertions.assertEquals("Frasqueirão", clube.getEstadio());
    }

    @Test
    void testShowClube() {
        Clube clube = this.clubeClient.show(1L).blockingGet();
        Assertions.assertEquals("America de Natal", clube.getNome());
        Assertions.assertEquals("Arena das Dunas", clube.getEstadio());
    }

}
