package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Dobavljac;
import rva.models.Porudzbina;
import rva.services.DobavljacService;
import rva.services.PorudzbinaService;

@RestController
public class PorudzbinaController {

	@Autowired
	private PorudzbinaService service;
	
	@Autowired
	private DobavljacService dobavljacService;

	@GetMapping("/porudzbinas")
	public ResponseEntity<?> getPorudzbinas(@RequestParam(required = false) Boolean placeno,
			@RequestParam(required = false) Double iznos,
			@RequestParam(required = false) Long id) {
		if (placeno != null && id == null && iznos == null) {
			List<Porudzbina> porudzbinas = service.getPorudzbinasByPlaceno(placeno);
			if (porudzbinas.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("No porudzbina is paid"));

			return ResponseEntity.ok(porudzbinas);
		} else if (placeno == null && id == null && iznos !=null) {
			List<Porudzbina> porudzbinas = service.getPorudzbinasByIznos(iznos);
			if (porudzbinas.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("No porudzbina has iznos less than: %s", iznos));

			return ResponseEntity.ok(porudzbinas);
		}else if(placeno == null && iznos == null && id != null) {
			Optional<Porudzbina> porudzbina = service.findById(id);
			if (porudzbina.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("No porudzbina found with an ID: %s", id));

			return ResponseEntity.ok(porudzbina.get());
		}
		
		else if (placeno!=null || id != null || iznos != null) {
			return ResponseEntity.badRequest().body("Request can only accept single query parameter");
		}

		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/porudzbinas")
	public ResponseEntity<?> createPorudzbina(@RequestBody Porudzbina porudzbina) {
		Porudzbina createdPorudzbina = service.create(porudzbina);
		URI uri = URI.create(String.format("/porudzbinas?id=%s", createdPorudzbina.getId()));
		return ResponseEntity.created(uri).body(createdPorudzbina);
	}

	@PutMapping("/porudzbinas")
	public ResponseEntity<?> updatePorudzbina(@RequestBody Porudzbina porudzbina, @RequestParam Long id) {
		Optional<Porudzbina> updatedPorudzbina = service.update(porudzbina, id);
		if (updatedPorudzbina.isEmpty())
			return ResponseEntity.status(404).body(String.format("Resource with an ID: %s does not exist", id));
		return ResponseEntity.ok(updatedPorudzbina.get());
	}

	@DeleteMapping("/porudzbinas")
	public ResponseEntity<?> deletePorudzbina(@RequestParam Long id) {
		if (service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.status(204).body(null);
		}
		return ResponseEntity.status(404).body(String.format("Resource with an ID: %s does not exist", id));
	}
	
	@GetMapping("/porudzbinas/dobavljac")
	public ResponseEntity<?> getPorudzbinaByDobavljac(@RequestParam Long dobavljacId){
		 Optional<Dobavljac> dobavljac =  dobavljacService.findById(dobavljacId);
		 if(dobavljac.isEmpty()) {
			 return ResponseEntity.status(404)
					 .body(String.format("Dobavljac with an ID: %s does not exist",
							 dobavljacId));
		 }
		List<Porudzbina> porudzbine =  service.getPorudzbinasByDobavljac(dobavljac.get());
		if(porudzbine.isEmpty()) {
			return ResponseEntity.status(404)
					 .body(String.format("Porudzbina with dobavljac ID: %s does not exist",
							 dobavljacId));
		}
		return ResponseEntity.ok(porudzbine);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
