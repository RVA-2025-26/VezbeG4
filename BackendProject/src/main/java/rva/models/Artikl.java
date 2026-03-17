package rva.models;

public class Artikl {

	private long id;
	private String naziv;
	private String proizvodjac;

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
