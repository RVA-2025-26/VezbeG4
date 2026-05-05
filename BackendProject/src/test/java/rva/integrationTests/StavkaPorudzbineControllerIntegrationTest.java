package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	String endPoint = "http://localhost:8080/stavkaPorudzbines";
	static long largestId = 0;

	@Test
	@Order(1)
	void getAllStavkas() {
		ResponseEntity<List<StavkaPorudzbine>> response = template.exchange(endPoint, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<StavkaPorudzbine>>() {
				});
		assertNotEquals(0, response.getBody().size());
		assertEquals(200, response.getStatusCode().value());
	}

	@Test
	@Order(2)
	void getStavkaById() {
		long id = 1;
		ResponseEntity<StavkaPorudzbine> response = template.getForEntity(endPoint + "?id=" + id,
				StavkaPorudzbine.class);

		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(3)
	void getStavkaByCenaLessThan() {
		double cena = 6000;
		ResponseEntity<List<StavkaPorudzbine>> response = template.exchange(endPoint + "?cena=" + cena, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<StavkaPorudzbine>>() {
				});

		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		for (StavkaPorudzbine s : response.getBody()) {
			assertTrue(s.getCena() < cena);
		}
	}

	@Test
	@Order(4)
	void getStavkasByArtikl() {
		long artiklId = 1;
		ResponseEntity<List<StavkaPorudzbine>> response = template.exchange(endPoint + "/artikl?artikl=" + artiklId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<StavkaPorudzbine>>() {
				});
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		for (StavkaPorudzbine s : response.getBody()) {
			assertEquals(artiklId, s.getArtikl().getId());
		}
	}

	@Test
	@Order(5)
	void createStavkaPorudzbine() {
		StavkaPorudzbine newStavka = new StavkaPorudzbine(5, 5, 5000, "test");

		ResponseEntity<StavkaPorudzbine> response = template.postForEntity(endPoint, newStavka, StavkaPorudzbine.class);
		assertEquals(5, response.getBody().getRedniBroj());
		assertEquals(201, response.getStatusCode().value());
		assertEquals("test", response.getBody().getJedinicaMere());
		
		if(largestId < response.getBody().getId()) largestId = response.getBody().getId();
		
	}
	
	@Test
	@Order(6)
	void updateStavkaPorudzbine() {
		StavkaPorudzbine updatedStavka = new StavkaPorudzbine(5, 5, 660000, "PUT");
		HttpEntity<StavkaPorudzbine> putEntity = new HttpEntity<StavkaPorudzbine>(updatedStavka);
		ResponseEntity<StavkaPorudzbine> response = 
				template.exchange(endPoint+"?id="+largestId,HttpMethod.PUT,putEntity,StavkaPorudzbine.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals("PUT", response.getBody().getJedinicaMere());
	}
	
	@Test
	@Order(7)
	void deleteStavkaPorudzbine() {
		 ResponseEntity<?> response = 
				 template.exchange(endPoint+"?id="+largestId, HttpMethod.DELETE, null, Object.class);
		 
		 assertEquals(204, response.getStatusCode().value());
		 assertNull(response.getBody());
	
	}
	
	
}
