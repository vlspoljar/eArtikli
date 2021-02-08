package main.java.hr.eartikli.model;

import main.java.hr.eartikli.enums.JedinicaMjere;

public class Artikl {

	// fields
	private String sifra;
	private String naziv;
	private JedinicaMjere jedinicaMjere;

	// constructors
	public Artikl() {
	}

	public Artikl(String sifra, String naziv, JedinicaMjere jedinicaMjere) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.jedinicaMjere = jedinicaMjere;
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

	public JedinicaMjere getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(JedinicaMjere jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	@Override
	public String toString() {
		return "Artikl{" +
			"sifra='" + sifra + '\'' +
			", naziv='" + naziv + '\'' +
			", jedinicaMjere=" + jedinicaMjere +
			'}';
	}
}
