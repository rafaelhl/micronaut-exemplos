package partidas.service;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import partidas.client.ClubesClient;
import partidas.domain.Partida;
import partidas.repository.PartidasRepository;

@Singleton
@AllArgsConstructor
public class PartidasService {

	private PartidasRepository partidasRepository;
	private ClubesClient clubesClient;

	public Single<Partida> salvarPartida(Partida partida) {
		return this.partidasRepository.save(partida);
	}

	public Flowable<PartidasResponse> obterPartidas() {
		return this.partidasRepository.findAll().flatMapMaybe(this::toResponse);
	}

	private Maybe<PartidasResponse> toResponse(Partida partida) {
		return Maybe.zip(
			this.clubesClient.show(partida.getClubeCasaId()),
			this.clubesClient.show(partida.getClubeVisitanteId()),
			(clubeCasa, clubeVisitante) -> new PartidasResponse(
				clubeCasa.getNome(),
				clubeVisitante.getNome(),
				clubeCasa.getEstadio(),
				partida.getPontuacaoClubeCasa(),
				partida.getPontuacaoClubeVisitante(),
				partida.getDataPartida()
			)
		);
	}

}
