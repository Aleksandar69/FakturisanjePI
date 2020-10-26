package com.aleksandar.fakturisanje.dto;

public class StavkaCjenovnikaDto {

	private long id;
	private float cijena;
	private long cjenovnik;
	private long robaUsluga;
	
	public StavkaCjenovnikaDto() {}

	public StavkaCjenovnikaDto(long id, float cijena, long cjenovnik, long robaUsluga) {
		super();
		this.id = id;
		this.cijena = cijena;
		this.cjenovnik = cjenovnik;
		this.robaUsluga = robaUsluga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCijena() {
		return cijena;
	}

	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	public long getCjenovnik() {
		return cjenovnik;
	}

	public void setCjenovnik(long cjenovnik) {
		this.cjenovnik = cjenovnik;
	}

	public long getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(long robaUsluga) {
		this.robaUsluga = robaUsluga;
	}
	
	
	
}
