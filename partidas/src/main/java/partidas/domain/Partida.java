package partidas.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
@Introspected
@NoArgsConstructor
public class Partida {

	@BsonId
	private ObjectId id;

	private Long clubeCasaId;
	private Long clubeVisitanteId;

	private Short pontuacaoClubeCasa;
	private Short pontuacaoClubeVisitante;

	private Date dataPartida;

	public Partida(@BsonProperty @JsonProperty Long clubeCasaId,
		@BsonProperty @JsonProperty Long clubeVisitanteId,
		@BsonProperty @JsonProperty Short pontuacaoClubeCasa,
		@BsonProperty @JsonProperty Short pontuacaoClubeVisitante,
		@BsonProperty @JsonProperty Date dataPartida) {
		this.clubeCasaId = clubeCasaId;
		this.clubeVisitanteId = clubeVisitanteId;
		this.pontuacaoClubeCasa = pontuacaoClubeCasa;
		this.pontuacaoClubeVisitante = pontuacaoClubeVisitante;
		this.dataPartida = dataPartida;
	}
}
