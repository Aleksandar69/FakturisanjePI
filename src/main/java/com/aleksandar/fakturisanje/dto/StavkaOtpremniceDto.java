package com.aleksandar.fakturisanje.dto;

public class StavkaOtpremniceDto {

	private long id;
	private String jedinicaMjere;
	private int kolicina;
	private float  cijena;
	private float iznos;
	private String opisRobe;
	private long robaUsluga;
	private long otpremnica;
	private boolean obrisano;
	
	public StavkaOtpremniceDto() {}

	public StavkaOtpremniceDto(long id, String jedinicaMjere, int kolicina, float cijena, float iznos, String opisRobe,
			long robaUsluga, long otpremnica, boolean obrisano) {
		super();
		this.id = id;
		this.jedinicaMjere = jedinicaMjere;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.iznos = iznos;
		this.opisRobe = opisRobe;
		this.robaUsluga = robaUsluga;
		this.otpremnica = otpremnica;
		this.obrisano = obrisano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(String jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public float getCijena() {
		return cijena;
	}

	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
	}

	public String getOpisRobe() {
		return opisRobe;
	}

	public void setOpisRobe(String opisRobe) {
		this.opisRobe = opisRobe;
	}

	public long getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(long robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public long getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(long otpremnica) {
		this.otpremnica = otpremnica;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
}
