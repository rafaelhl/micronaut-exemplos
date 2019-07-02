package partidas.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import partidas.domain.Partida;
import partidas.service.PartidasResponse;
import partidas.service.PartidasService;

@AllArgsConstructor
@Controller("/partidas")
public class PartidasController {

    private PartidasService partidasService;

    @Post
    public Single<Partida> salvar(Partida partida) {
        return this.partidasService.salvarPartida(partida);
    }

    @Get
    public Flowable<PartidasResponse> index() {
        return this.partidasService.obterPartidas();
    }

}