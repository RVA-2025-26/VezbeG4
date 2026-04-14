package rva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import rva.models.StavkaPorudzbine;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StavkaPorudzbineControllerIntegrationTest {
	
	RestTemplate template = new RestTemplate();
	String apiUrl = "http://localhost:8080/stavkaPorudzbines";
	static int largestId = 0;

	@Test
	@Order(1)
	void getAllStavkas() {
		ResponseEntity<List<StavkaPorudzbine>> response  =
		template.exchange(apiUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<StavkaPorudzbine>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
	}
	
	@Test
	@Order(2)
	void getStavkaById() {
		long id = 1;
		ResponseEntity<StavkaPorudzbine> response = 
				template.getForEntity(apiUrl + "?id=" + id, StavkaPorudzbine.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(id, response.getBody().getId());
	}
	
	@Test
	@Order(3)
	void getStavkaByCenaLessThan() {
		double cena = 6000;
		ResponseEntity<List<StavkaPorudzbine>> response = 
		template.exchange(apiUrl + "?cena=" + cena, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<StavkaPorudzbine>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		for(StavkaPorudzbine s : response.getBody()) {
			assertTrue(s.getCena() < cena);
		}
	}
	
	@Test
	@Order(4)
	void getStavkaByArtikl() {
		int artiklId = 1;
		ResponseEntity<List<StavkaPorudzbine>> response = 
				template.exchange(apiUrl + "/artikl?artiklId=" + artiklId, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<StavkaPorudzbine>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		for(StavkaPorudzbine s : response.getBody()) {
			assertEquals(artiklId, s.getArtikl().getId());
		}
	}
	
	@Test
	@Order(5)
	void getStavkaByPorudzbina() {
		int porudzbinaId = 1;
		ResponseEntity<List<StavkaPorudzbine>> response = 
				template.exchange(apiUrl + "/porudzbina?porudzbinaId=" + porudzbinaId, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<StavkaPorudzbine>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		for(StavkaPorudzbine s : response.getBody()) {
			assertEquals(porudzbinaId, s.getPorudzbina().getId());
		}
	}
	
	@Test
	@Order(6)
	void createStavka() {
		StavkaPorudzbine stavka = new StavkaPorudzbine();
		stavka.setCena(5000);
		stavka.setJedinicaMere("POST TEST");
		
		HttpEntity<StavkaPorudzbine> entity = new HttpEntity<StavkaPorudzbine>(stavka);
		
		ResponseEntity<StavkaPorudzbine> response =
		template.exchange(apiUrl, HttpMethod.POST, entity, StavkaPorudzbine.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(stavka.getCena(), response.getBody().getCena());
		assertEquals(stavka.getJedinicaMere(), response.getBody().getJedinicaMere());
		
		if(largestId < response.getBody().getId()) largestId = (int) response.getBody().getId();
	}
	
	@Test
	@Order(7)
	void updateStavka() {
		StavkaPorudzbine stavka = new StavkaPorudzbine();
		stavka.setCena(55000);
		stavka.setJedinicaMere("PUT TEST");
		
		HttpEntity<StavkaPorudzbine> entity = new HttpEntity<StavkaPorudzbine>(stavka);
		
		ResponseEntity<StavkaPorudzbine> response =
		template.exchange(apiUrl +"?id=" + largestId, HttpMethod.PUT, entity, StavkaPorudzbine.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(stavka.getCena(), response.getBody().getCena());
		assertEquals(stavka.getJedinicaMere(), response.getBody().getJedinicaMere());
		
	}
	
	@Test
	@Order(8)
	void deleteStavka() {
		
		ResponseEntity<?> response = 
		template.exchange(apiUrl +"?id=" + largestId, HttpMethod.DELETE, null, Object.class);
		
		assertEquals(204, response.getStatusCode().value());
		assertNull(response.getBody());
	}
	

}
