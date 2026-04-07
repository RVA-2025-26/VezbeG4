package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Artikl;
import rva.repositories.ArtiklRepository;
import rva.services.ArtiklService;

@Component
public class ArtiklServiceImpl implements ArtiklService {

	@Autowired
	private ArtiklRepository repo;
	
	@Override
	public List<Artikl> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Artikl create(Artikl body) {
		return repo.save(body);
	}

	@Override
	public Optional<Artikl> update(Artikl body, long id) {
		if(existsById(id)) {
			body.setId(id);
			return Optional.of(repo.save(body));
		}
		return Optional.empty();
	}

	@Override
	public void delete(long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Artikl> getArtiklsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public Optional<Artikl> findById(long id) {
		return repo.findById(id);
	}

}
