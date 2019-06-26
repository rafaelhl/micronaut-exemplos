package clubes.service;

import clubes.domain.Clube;
import clubes.repository.ClubesRepository;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class ClubesService {

	private ClubesRepository clubesRepository;
	
	public Flowable<Clube> findAll() {
		return this.clubesRepository.findAll();
	}

	public Maybe<Clube> findClube(Long id) {
		return this.clubesRepository.findClube(id);
	}
}