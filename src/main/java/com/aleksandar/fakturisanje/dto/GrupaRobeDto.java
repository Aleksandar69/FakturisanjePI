package com.aleksandar.fakturisanje.dto;

public class GrupaRobeDto {

	private long id;
	private String nazivGrupe;
	private long preduzece;
	private long pdv;
	
	public GrupaRobeDto() {}

	public GrupaRobeDto(long id, String nazivGrupe, long preduzece, long pdv) {
		super();
		this.id = id;
		this.nazivGrupe = nazivGrupe;
		this.preduzece = preduzece;
		this.pdv = pdv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}

	public long getPdv() {
		return pdv;
	}

	public void setPdv(long pdv) {
		this.pdv = pdv;
	}
	
	
}

