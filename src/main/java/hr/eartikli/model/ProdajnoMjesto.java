package main.java.hr.eartikli.model;

public class ProdajnoMjesto {

	// fields
	private String sifra;
	private String naziv;

	// constructors
	public ProdajnoMjesto() {
	}

	public ProdajnoMjesto(String sifra, String naziv) {
		this.sifra = sifra;
		this.naziv = naziv;
	}

	// getters and setters
	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "ProdajnoMjesto{" +
			"sifra='" + sifra + '\'' +
			", naziv='" + naziv + '\'' +
			'}';
	}
}
