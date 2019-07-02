package partidas.service;

import java.util.Date;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Introspected
@NoArgsConstructor
@AllArgsConstructor
public class PartidasResponse {
	private String clubeCasa, clubeVisitante, estadio;
	private Short pontuacaoClubeCasa, pontuacaoClubeVisitante;
	private Date data;
}
