package com.aleksandar.fakturisanje.dto;

import java.util.Date;

public class NarudzbenicaDto {

	private long id;
	private long brojNarudzbenice;
	private Date datumNarudzbenice;
	private long preduzece;
	private long poslovniPartner;
	private long poslovnaGodina;
	private boolean tipNarudzbenice;
	private boolean obrisano;
	
	public NarudzbenicaDto() {}

	public NarudzbenicaDto(long id, long brojNarudzbenice, Date datumNarudzbenice, long preduzece, long poslovniPartner,
			long poslovnaGodina, boolean tipNarudzbenice, boolean obrisano) {
		super();
		this.id = id;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumNarudzbenice = datumNarudzbenice;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.tipNarudzbenice = tipNarudzbenice;
		this.obrisano = obrisano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojNarudzbenice() {
		return brojNarudzbenice;
	}

	public void setBrojNarudzbenice(long brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
	}

	public Date getDatumNarudzbenice() {
		return datumNarudzbenice;
	}

	public void setDatumNarudzbenice(Date datumNarudzbenice) {
		this.datumNarudzbenice = datumNarudzbenice;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}

	public long getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(long poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}

	public long getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(long poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public boolean isTipNarudzbenice() {
		return tipNarudzbenice;
	}

	public void setTipNarudzbenice(boolean tipNarudzbenice) {
		this.tipNarudzbenice = tipNarudzbenice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
	
}
