package com.aleksandar.fakturisanje.dto;

import java.util.Date;

public class StopaPdvDto {

	private long id;
	private float procenat;
	private Date datumVazenja;
	private long pdv;
	
	public StopaPdvDto() {}

	public StopaPdvDto(long id, float procenat, Date datumVazenja, long pdv) {
		super();
		this.id = id;
		this.procenat = procenat;
		this.datumVazenja = datumVazenja;
		this.pdv = pdv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getProcenat() {
		return procenat;
	}

	public void setProcenat(float procenat) {
		this.procenat = procenat;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public long getPdv() {
		return pdv;
	}

	public void setPdv(long pdv) {
		this.pdv = pdv;
	}
	
	
}
