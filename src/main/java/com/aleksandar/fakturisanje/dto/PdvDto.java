package com.aleksandar.fakturisanje.dto;

public class PdvDto {

	private long id;
	private String nazivPdva;
	
	public PdvDto() {}
	
	public PdvDto(int id, String nazivPdv) {
		super();
		this.id = id;
		this.nazivPdva = nazivPdv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivPdva() {
		return nazivPdva;
	}

	public void setNazivPdva(String nazivPdva) {
		this.nazivPdva = nazivPdva;
	}
	
	
}
