package com.aleksandar.fakturisanje.dto;

public class PoslovniPartnerDto {

	private long id;
	private String nazivPartnera;
	private String adresa;
	private int vrstaPartnera;
	private String tekuciRacun;
	private String PIB;
	private long preduzece;
	private long mjesto;
	
	public PoslovniPartnerDto() {}

	public PoslovniPartnerDto(long id, String nazivPartnera, String adresa, int vrstaPartnera, String tekuciRacun,
			String pIB, long preduzece, long mjesto) {
		super();
		this.id = id;
		this.nazivPartnera = nazivPartnera;
		this.adresa = adresa;
		this.vrstaPartnera = vrstaPartnera;
		this.tekuciRacun = tekuciRacun;
		PIB = pIB;
		this.preduzece = preduzece;
		this.mjesto = mjesto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivPartnera() {
		return nazivPartnera;
	}

	public void setNazivPartnera(String nazivPartnera) {
		this.nazivPartnera = nazivPartnera;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getVrstaPartnera() {
		return vrstaPartnera;
	}

	public void setVrstaPartnera(int vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}

	public long getMjesto() {
		return mjesto;
	}

	public void setMjesto(long mjesto) {
		this.mjesto = mjesto;
	}
	
	
	
}
