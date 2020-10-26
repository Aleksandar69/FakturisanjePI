package com.aleksandar.fakturisanje.dto;

public class PreduzeceDto {

	private long id;
	private String naziv;
	private String adresaPreduzeca;
	private String PIB;
	private String telefon;
	private String email;
	private String tekuciRacun;
	private String logo;
	private long mjesto;
	
	public PreduzeceDto() {}

	public PreduzeceDto(long id, String naziv, String adresaPreduzeca, String pIB, String telefon, String email,
			String tekuciRacun, String logo, long mjesto) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresaPreduzeca = adresaPreduzeca;
		PIB = pIB;
		this.telefon = telefon;
		this.email = email;
		this.tekuciRacun = tekuciRacun;
		this.logo = logo;
		this.mjesto = mjesto;
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

	public String getAdresaPreduzeca() {
		return adresaPreduzeca;
	}

	public void setAdresaPreduzeca(String adresaPreduzeca) {
		this.adresaPreduzeca = adresaPreduzeca;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public long getMjesto() {
		return mjesto;
	}

	public void setMjesto(long mjesto) {
		this.mjesto = mjesto;
	}
	
	
	
}
