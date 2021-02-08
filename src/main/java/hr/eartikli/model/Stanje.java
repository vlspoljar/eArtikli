package main.java.hr.eartikli.model;

import java.math.BigDecimal;

public class Stanje {

	// fields
	private String sifraArtikla;
	private String sifraProdajnogMjesta;
	private BigDecimal kolicinaArtikla;

	// constructors
	public Stanje() {
	}

	public Stanje(String sifraArtikla, String sifraProdajnogMjesta, BigDecimal kolicinaArtikla) {
		this.sifraArtikla = sifraArtikla;
		this.sifraProdajnogMjesta = sifraProdajnogMjesta;
		this.kolicinaArtikla = kolicinaArtikla;
	}

	// getters and setters
	public String getSifraArtikla() {
		return sifraArtikla;
	}

	public void setSifraArtikla(String sifraArtikla) {
		this.sifraArtikla = sifraArtikla;
	}

	public String getSifraProdajnogMjesta() {
		return sifraProdajnogMjesta;
	}

	public void setSifraProdajnogMjesta(String sifraProdajnogMjesta) {
		this.sifraProdajnogMjesta = sifraProdajnogMjesta;
	}

	public BigDecimal getKolicinaArtikla() {
		return kolicinaArtikla;
	}

	public void setKolicinaArtikla(BigDecimal kolicinaArtikla) {
		this.kolicinaArtikla = kolicinaArtikla;
	}

	@Override
	public String toString() {
		return "Stanje{" +
			"sifraArtikla='" + sifraArtikla + '\'' +
			", sifraProdajnogMjesta='" + sifraProdajnogMjesta + '\'' +
			", kolicinaArtikla=" + kolicinaArtikla +
			'}';
	}
}
