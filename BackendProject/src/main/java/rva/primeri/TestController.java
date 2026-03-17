package rva.primeri;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/zbir")
	public String sabiranje() {
		return String.valueOf(Math.random() * 10 + Math.random()*10);
	}
	
	@GetMapping("/zbir-dva-broja")
	public String sabiranjeDvaBroja(@RequestParam double prviBroj,
			@RequestParam double drugiBroj) {
		return String.valueOf(prviBroj + drugiBroj);
	}
}
