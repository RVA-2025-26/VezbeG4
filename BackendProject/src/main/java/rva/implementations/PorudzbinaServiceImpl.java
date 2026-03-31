package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Dobavljac;
import rva.models.Porudzbina;
import rva.repositories.PorudzbinaRepository;
import rva.services.PorudzbinaService;

@Component
public class PorudzbinaServiceImpl implements PorudzbinaService {

	@Autowired
	private PorudzbinaRepository repo;
	
	@Override
	public List<Porudzbina> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Porudzbina create(Porudzbina body) {
		return repo.save(body);
	}

	@Override
	public Optional<Porudzbina> update(Porudzbina body, long id) {
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
	public List<Porudzbina> getPorudzbinasByPlaceno(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}

	@Override
	public List<Porudzbina> getPorudzbinasByIznos(double iznos) {
		return repo.findByIznosLessThan(iznos);
	}

	@Override
	public List<Porudzbina> getPorudzbinasByDobavljac(Dobavljac dobavljac) {
		return repo.findByDobavljac(dobavljac);
	}

}
