package com.aleksandar.fakturisanje.dto;

import java.util.Date;

public class CjenovnikDto {

	private long id;
	
	private Date datumVazenjaOd;
	
	private Date DatumVazenjaDo;
	
	private long preduzeceId;
	
	private long poslovniPartnerId;
	
	public CjenovnikDto() {
		
	}
	
	

	public CjenovnikDto(long id, Date datumVazenja, Date datumVazenjaDo) {
		super();
		this.id = id;
		this.datumVazenjaOd = datumVazenja;
		this.DatumVazenjaDo = datumVazenjaDo;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenjaOd() {
		return datumVazenjaOd;
	}

	public void setDatumVazenjaOd(Date datumVazenja) {
		this.datumVazenjaOd = datumVazenja;
	}



	public Date getDatumVazenjaDo() {
		return DatumVazenjaDo;
	}



	public void setDatumVazenjaDo(Date datumVazenjaDo) {
		DatumVazenjaDo = datumVazenjaDo;
	}



	public long getPreduzeceId() {
		return preduzeceId;
	}



	public void setPreduzeceId(long preduzeceId) {
		this.preduzeceId = preduzeceId;
	}



	public long getPoslovniPartnerId() {
		return poslovniPartnerId;
	}



	public void setPoslovniPartnerId(long poslovniPartnerId) {
		this.poslovniPartnerId = poslovniPartnerId;
	}
	
	
	
	
}
