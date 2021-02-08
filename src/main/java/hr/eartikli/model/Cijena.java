package main.java.hr.eartikli.model;

import java.math.BigDecimal;

public class Cijena {

	// fields
	private String sifraArtikla;
	private BigDecimal cijenaArtikla = BigDecimal.ZERO;

	// constructors
	public Cijena() {
	}

	public Cijena(String sifraArtikla, BigDecimal cijenaArtikla) {
		this.sifraArtikla = sifraArtikla;
		this.cijenaArtikla = cijenaArtikla;
	}

	// getters and setters
	public String getSifraArtikla() {
		return sifraArtikla;
	}

	public void setSifraArtikla(String sifraArtikla) {
		this.sifraArtikla = sifraArtikla;
	}

	public BigDecimal getCijenaArtikla() {
		return cijenaArtikla;
	}

	public void setCijenaArtikla(BigDecimal cijenaArtikla) {
		this.cijenaArtikla = cijenaArtikla;
	}

	@Override
	public String toString() {
		return "Cijena{" +
			"sifraArtikla='" + sifraArtikla + '\'' +
			", cijenaArtikla=" + cijenaArtikla +
			'}';
	}
}
