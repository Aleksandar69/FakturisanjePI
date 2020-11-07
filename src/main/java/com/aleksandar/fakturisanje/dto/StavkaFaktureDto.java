package com.aleksandar.fakturisanje.dto;

public class StavkaFaktureDto {

	private long id;
	private long kolicina;
	private float cijena;
	private double rabat;
	private double osnovicaZaPdv;
	private double procenatPdv;
	private double iznosPdv;
	private double iznosStavka;
	private long faktura;
	private long robaUsluga;
	
	public StavkaFaktureDto() {}

	public StavkaFaktureDto(long id, long kolicina, float cijena, double rabat, double osnovicaZaPdv,
			double procenatPdv, double iznosPdv, double inosStavka, long faktura, long robaUsluga) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.rabat = rabat;
		this.osnovicaZaPdv = osnovicaZaPdv;
		this.procenatPdv = procenatPdv;
		this.iznosPdv = iznosPdv;
		this.iznosStavka = inosStavka;
		this.faktura = faktura;
		this.robaUsluga = robaUsluga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
	}

	public float getCijena() {
		return cijena;
	}

	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getOsnovicaZaPdv() {
		return osnovicaZaPdv;
	}

	public void setOsnovicaZaPdv(double osnovicaZaPdv) {
		this.osnovicaZaPdv = osnovicaZaPdv;
	}

	public double getProcenatPdv() {
		return procenatPdv;
	}

	public void setProcenatPdv(double procenatPdv) {
		this.procenatPdv = procenatPdv;
	}

	public double getIznosPdv() {
		return iznosPdv;
	}

	public void setIznosPdv(double iznosPdv) {
		this.iznosPdv = iznosPdv;
	}

	public double getIznosStavka() {
		return iznosStavka;
	}

	public void setIznosStavka(double iznosStavka) {
		this.iznosStavka = iznosStavka;
	}

	public long getFaktura() {
		return faktura;
	}

	public void setFaktura(long faktura) {
		this.faktura = faktura;
	}

	public long getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(long robaUsluga) {
		this.robaUsluga = robaUsluga;
	}
	
	
	
}
