package com.aleksandar.fakturisanje.dto;

public class PoslovnaGodinaDto {

	private long id;
	private int godina;
	private boolean zakljucana;
	
	public PoslovnaGodinaDto() {}

	public PoslovnaGodinaDto(long id, int godina, boolean zakljucana) {
		super();
		this.id = id;
		this.godina = godina;
		this.zakljucana = zakljucana;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public boolean isZakljucana() {
		return zakljucana;
	}

	public void setZakljucana(boolean zakljucana) {
		this.zakljucana = zakljucana;
	}
	
}
