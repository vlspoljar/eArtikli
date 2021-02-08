package main.java.hr.eartikli.model;

import java.math.BigDecimal;

public class StanjeExt extends Stanje {

	// fields
	private BigDecimal cijenaArtikla;
	private BigDecimal ukupnaVrijednostArtikla;

	// constructors
	public StanjeExt() {
	}

	public StanjeExt(String sifraArtikla, String sifraProdajnogMjesta, BigDecimal kolicinaArtikla, BigDecimal cijenaArtikla, BigDecimal ukupnaVrijednostArtikla) {
		super(sifraArtikla, sifraProdajnogMjesta, kolicinaArtikla);
		this.cijenaArtikla = cijenaArtikla;
		this.ukupnaVrijednostArtikla = ukupnaVrijednostArtikla;
	}

	// getters and setters
	public BigDecimal getCijenaArtikla() {
		return cijenaArtikla;
	}

	public void setCijenaArtikla(BigDecimal cijenaArtikla) {
		this.cijenaArtikla = cijenaArtikla;
	}

	public BigDecimal getUkupnaVrijednostArtikla() {
		return ukupnaVrijednostArtikla;
	}

	public void setUkupnaVrijednostArtikla(BigDecimal ukupnaVrijednostArtikla) {
		this.ukupnaVrijednostArtikla = ukupnaVrijednostArtikla;
	}

	@Override
	public String toString() {
		return "StanjeExt{" +
			"sifraArtikla=" + super.getSifraArtikla() + '\'' +
			"sifraProdajnogMjesta=" + super.getSifraProdajnogMjesta() + '\'' +
			"kolicinaArtikla=" + super.getKolicinaArtikla() + '\'' +
			"cijenaArtikla=" + cijenaArtikla + '\'' +
			"ukupnaVrijednostArtikla=" + ukupnaVrijednostArtikla +
			'}';
	}
}
