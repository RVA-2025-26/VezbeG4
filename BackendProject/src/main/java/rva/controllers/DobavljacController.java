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
import rva.services.DobavljacService;

@RestController
public class DobavljacController {

	
	@Autowired
	private DobavljacService service;
	
	@GetMapping("/dobavljacs")
	public ResponseEntity<?> getDobavljacs(@RequestParam(required = false) String naziv,
			@RequestParam(required = false) Long id){
		if(naziv != null && id == null) {
			List<Dobavljac> dobavljacs  = service.getDobavljacsByNaziv(naziv);
			if(dobavljacs.isEmpty()) 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("No dobavljacs found for naziv: %s", naziv));
			
			return ResponseEntity.ok(dobavljacs);
		}else if(naziv == null && id!=null) {
			Optional<Dobavljac> dobavljac = service.findById(id);
			if(dobavljac.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("No dobavljacs found with ID: %s", id));
			
			return ResponseEntity.ok(dobavljac.get());
		}else if(naziv != null && id !=null ) {
			return ResponseEntity.badRequest().body("Request can only accept either ID or Naziv");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/dobavljacs")
	public ResponseEntity<?> createDobavljac(@RequestBody Dobavljac dobavljac){
		Dobavljac createdDobavljac = service.create(dobavljac);
		URI uri = URI.create(String.format("/dobavljacs?id=%s", createdDobavljac.getId()));
		return ResponseEntity.created(uri).body(createdDobavljac);
	}
	
	@PutMapping("/dobavljacs")
	public ResponseEntity<?> updateDobavljac(@RequestBody Dobavljac dobavljac, @RequestParam Long id){
		Optional<Dobavljac> updatedDobavljac = service.update(dobavljac, id);
		if(updatedDobavljac.isEmpty())
			return ResponseEntity.status(404)
					.body(String.format("Resource with an ID: %s does not exist", id));
		return ResponseEntity.ok(updatedDobavljac.get());
	}
	
	@DeleteMapping("/dobavljacs")
	public ResponseEntity<?> deleteDobavljac(@RequestParam Long id){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.status(204).body(null);
		}
		return ResponseEntity.status(404)
				.body(String.format("Resource with an ID: %s does not exist", id));
	}
}
