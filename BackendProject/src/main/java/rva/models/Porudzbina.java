package rva.models;

import java.util.Date;

public class Porudzbina {

	private long id;
	private Date datumPorudzbine;
	private Date datumIsporuke;
	private boolean placeno;
	private double iznos;

	public Porudzbina(long id, Date datumPorudzbine, Date datumIsporuke, boolean placeno, double iznos) {
		super();
		this.id = id;
		this.datumPorudzbine = datumPorudzbine;
		this.datumIsporuke = datumIsporuke;
		this.placeno = placeno;
		this.iznos = iznos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumPorudzbine() {
		return datumPorudzbine;
	}

	public void setDatumPorudzbine(Date datumPorudzbine) {
		this.datumPorudzbine = datumPorudzbine;
	}

	public Date getDatumIsporuke() {
		return datumIsporuke;
	}

	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

}
