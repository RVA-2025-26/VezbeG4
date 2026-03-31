package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Artikl;
import rva.models.Porudzbina;
import rva.models.StavkaPorudzbine;
import rva.repositories.StavkaPorudzbineRepository;
import rva.services.StavkaPorudzbineService;

@Component
public class StavkaPorudzbineServiceImpl implements StavkaPorudzbineService {

	@Autowired
	private StavkaPorudzbineRepository repo;
	
	@Override
	public List<StavkaPorudzbine> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public StavkaPorudzbine create(StavkaPorudzbine body) {
		return repo.save(body);
	}

	@Override
	public Optional<StavkaPorudzbine> update(StavkaPorudzbine body, long id) {
		if(existsById(id)) {
			body.setId(id);
			Optional.of(repo.save(body));
		}
		return Optional.empty();
	}

	@Override
	public void delete(long id) {
		repo.deleteById(id);

	}

	@Override
	public List<StavkaPorudzbine> getStavkasByCena(double cena) {
		return repo.findByCenaLessThan(cena);
	}

	@Override
	public List<StavkaPorudzbine> getStavkasByArtikl(Artikl artikl) {
		return repo.findByArtikl(artikl);
	}

	@Override
	public List<StavkaPorudzbine> getStavkasByPorudzbina(Porudzbina porudzbina) {
		return repo.findByPorudzbina(porudzbina);
	}

}
