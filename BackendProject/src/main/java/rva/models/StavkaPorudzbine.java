package rva.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class StavkaPorudzbine {

	@Id
	@SequenceGenerator(name = "stavka_porudzbine_seq", sequenceName = "stavka_porudzbine_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stavka_porudzbine_seq")
	private long id;
	private int redniBroj;
	private double kolicina;
	private double cena;
	private String jedinicaMere;
	
	@ManyToOne
	@JoinColumn(name = "artikl")
	private Artikl artikl;
	
	@ManyToOne
	@JoinColumn(name = "porudzbina")
	private Porudzbina porudzbina;
	
	public StavkaPorudzbine() {
		
	}

	public StavkaPorudzbine(long id, int redniBroj, double kolicina, double cena,
			String jedinicaMere, Artikl artikl, Porudzbina porudzbina) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.kolicina = kolicina;
		this.cena = cena;
		this.jedinicaMere = jedinicaMere;
		this.artikl = artikl;
		this.porudzbina = porudzbina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	
}
