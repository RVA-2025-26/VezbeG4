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

import rva.models.Artikl;
import rva.models.Porudzbina;
import rva.models.StavkaPorudzbine;
import rva.services.ArtiklService;
import rva.services.PorudzbinaService;
import rva.services.StavkaPorudzbineService;

@RestController
public class StavkaPorudzbineController {

	@Autowired
	private StavkaPorudzbineService service;
	
	@Autowired
	private ArtiklService artiklService;
	
	@Autowired
	private PorudzbinaService porudzbinaService;
	
	
	@GetMapping("/stavkaPorudzbines")
	public ResponseEntity<?> getStavkaPorudzbines(@RequestParam(required = false) Double cena,
			@RequestParam(required = false) Long id){
		if(cena != null && id == null) {
			List<StavkaPorudzbine> stavkaPorudzbines  = service.getStavkasByCena(cena);
			if(stavkaPorudzbines.isEmpty()) 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("No stavkas found with cena less than: %s", cena));
			
			return ResponseEntity.ok(stavkaPorudzbines);
		}else if(cena == null && id!=null) {
			Optional<StavkaPorudzbine> stavkaPorudzbine = service.findById(id);
			if(stavkaPorudzbine.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("No stavkaPorudzbines found with ID: %s", id));
			
			return ResponseEntity.ok(stavkaPorudzbine.get());
		}else if(cena != null && id !=null ) {
			return ResponseEntity.badRequest().body("Request can only accept either ID or cena");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/stavkaPorudzbines")
	public ResponseEntity<?> createStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine){
		StavkaPorudzbine createdStavkaPorudzbine = service.create(stavkaPorudzbine);
		URI uri = URI.create(String.format("/stavkaPorudzbines?id=%s", createdStavkaPorudzbine.getId()));
		return ResponseEntity.created(uri).body(createdStavkaPorudzbine);
	}
	
	@PutMapping("/stavkaPorudzbines")
	public ResponseEntity<?> updateStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine, @RequestParam Long id){
		Optional<StavkaPorudzbine> updatedStavkaPorudzbine = service.update(stavkaPorudzbine, id);
		if(updatedStavkaPorudzbine.isEmpty())
			return ResponseEntity.status(404)
					.body(String.format("Resource with an ID: %s does not exist", id));
		return ResponseEntity.ok(updatedStavkaPorudzbine.get());
	}
	
	@DeleteMapping("/stavkaPorudzbines")
	public ResponseEntity<?> deleteStavkaPorudzbine(@RequestParam Long id){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.status(204).body(null);
		}
		return ResponseEntity.status(404)
				.body(String.format("Resource with an ID: %s does not exist", id));
	}
	
	@GetMapping("/stavkaPorudzbines/artikl")
	public ResponseEntity<?> getStavkaPorudzbineByArtikl(@RequestParam Long artiklId){
		 Optional<Artikl> artikl =  artiklService.findById(artiklId);
		 if(artikl.isEmpty()) {
			 return ResponseEntity.status(404)
					 .body(String.format("Artikl with an ID: %s does not exist",
							 artiklId));
		 }
		List<StavkaPorudzbine> stavke =  service.getStavkasByArtikl(artikl.get());
		if(stavke.isEmpty()) {
			return ResponseEntity.status(404)
					 .body(String.format("Stavka with artikl ID: %s does not exist",
							 artiklId));
		}
		return ResponseEntity.ok(stavke);
	}
	
	@GetMapping("/stavkaPorudzbines/porudzbina")
	public ResponseEntity<?> getStavkaPorudzbineByPorudzbina(@RequestParam Long porudzbinaId){
		 Optional<Porudzbina> porudzbina =  porudzbinaService.findById(porudzbinaId);
		 if(porudzbina.isEmpty()) {
			 return ResponseEntity.status(404)
					 .body(String.format("Porudzbina with an ID: %s does not exist",
							 porudzbinaId));
		 }
		List<StavkaPorudzbine> stavke =  service.getStavkasByPorudzbina(porudzbina.get());
		if(stavke.isEmpty()) {
			return ResponseEntity.status(404)
					 .body(String.format("Stavka with porudzbina ID: %s does not exist",
							 porudzbinaId));
		}
		return ResponseEntity.ok(stavke);
	}
}
