package com.aleksandar.fakturisanje.dto;

import java.util.Date;

public class CjenovnikDto {

	private long id;
	
	private Date datumVazenja;
	
	public CjenovnikDto() {
		
	}
	
	

	public CjenovnikDto(long id, Date datumVazenja) {
		super();
		this.id = id;
		this.datumVazenja = datumVazenja;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}
	
	
	
}
