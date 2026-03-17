package rva.models;

public class Dobavljac {

	private long id;
	private String naziv;
	private String kontakt;
	private String adresa;

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
