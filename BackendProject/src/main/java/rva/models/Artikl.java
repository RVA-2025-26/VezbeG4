package rva.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Artikl {

	@Id
	@SequenceGenerator(name = "artikl_seq", sequenceName = "artikl_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artikl_seq")
	private long id;
	private String naziv;
	private String proizvodjac;
	
	@OneToMany(mappedBy ="artikl")
	private List<StavkaPorudzbine> stavke;

	public Artikl(long id, String naziv, String proizvodjac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.proizvodjac = proizvodjac;
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

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

}
