package clubes.init;

import clubes.domain.Clube;
import clubes.repository.ClubesRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.AllArgsConstructor;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class DataCreator implements ApplicationEventListener<ServerStartupEvent> {

	private ClubesRepository clubesRepository;

	@Override
	public void onApplicationEvent(ServerStartupEvent event) {
		if (this.clubesRepository.count().blockingGet() > 0) {
			return;
		}
		this.clubesRepository.save(new Clube("Avai", "Ilha"));
		this.clubesRepository.save(new Clube("Figueirense", "Continente"));
	}
}
