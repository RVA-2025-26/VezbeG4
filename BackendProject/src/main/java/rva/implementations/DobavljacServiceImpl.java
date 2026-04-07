package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Dobavljac;
import rva.repositories.DobavljacRepository;
import rva.services.DobavljacService;

@Component
public class DobavljacServiceImpl implements DobavljacService {

	@Autowired
	private DobavljacRepository repo;
	
	@Override
	public List<Dobavljac> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Dobavljac create(Dobavljac body) {
		return repo.save(body);
	}

	@Override
	public Optional<Dobavljac> update(Dobavljac body, long id) {
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
	public List<Dobavljac> getDobavljacsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public Optional<Dobavljac> findById(long id) {
		return repo.findById(id);
	}

}
