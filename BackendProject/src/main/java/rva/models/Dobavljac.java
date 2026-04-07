package rva.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Dobavljac {

	@Id
	@SequenceGenerator(name = "dobavljac_seq", sequenceName = "dobavljac_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dobavljac_seq")
	private long id;
	private String naziv;
	private String kontakt;
	private String adresa;
	
	@OneToMany(mappedBy="dobavljac", cascade = CascadeType.ALL)
	private List<Porudzbina> porudzbine;
	
	public Dobavljac() {
		
	}

	public Dobavljac(long id, String naziv, String kontakt, String adresa) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.kontakt = kontakt;
		this.adresa = adresa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
