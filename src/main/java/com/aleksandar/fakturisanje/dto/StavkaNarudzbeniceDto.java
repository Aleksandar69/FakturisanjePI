package com.aleksandar.fakturisanje.dto;

public class StavkaNarudzbeniceDto {

	private long id;
	private String jedinicaMjere;
	private int kolicina;
	private String opisRobe;
	private long robaUsluga;
	private long narudzbenica;
	private boolean obrisano;
	
	public StavkaNarudzbeniceDto() {}

	public StavkaNarudzbeniceDto(long id, String jedinicaMjere, int kolicina, String opisRobe, long robaUsluga,
			long narudzbenica, boolean obrisano) {
		super();
		this.id = id;
		this.jedinicaMjere = jedinicaMjere;
		this.kolicina = kolicina;
		this.opisRobe = opisRobe;
		this.robaUsluga = robaUsluga;
		this.narudzbenica = narudzbenica;
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

	public long getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(long narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
}
