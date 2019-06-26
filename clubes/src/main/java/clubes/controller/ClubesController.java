package clubes.controller;

import clubes.api.ClubesApi;
import clubes.domain.Clube;
import clubes.service.ClubesService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller("/clubes")
public class ClubesController implements ClubesApi {

    private ClubesService clubesService;

    @Get
    @Override
    public Flowable<Clube> index() {
        return this.clubesService.findAll();
    }

    @Get("/{identificador}")
    @Override
    public Maybe<Clube> show(Long identificador) {
        return this.clubesService.findClube(identificador);
    }

}