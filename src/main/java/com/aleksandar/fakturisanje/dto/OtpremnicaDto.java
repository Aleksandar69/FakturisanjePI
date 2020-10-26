package com.aleksandar.fakturisanje.dto;

import java.util.Date;

public class OtpremnicaDto {

	private long id;
	private long brojOtpremnice;
	private Date datumOtpremnice;
	private double iznosZaUplatu;
	private long preduzece;
	private long poslovniPartner;
	private long poslovnaGodina;
	private long narudzbenica;
	private boolean tipOtpremnice;
	private boolean obrisano;
	
	public OtpremnicaDto() {}
	
	public OtpremnicaDto(long id, long brojOtpremnice, Date datumOtpremnice, double iznosZaUplatu, long preduzece,
			long poslovniPartner, long poslovnaGodina, long narudzbenica, boolean tipOtpremnice, boolean obrisano) {
		super();
		this.id = id;
		this.brojOtpremnice = brojOtpremnice;
		this.datumOtpremnice = datumOtpremnice;
		this.iznosZaUplatu = iznosZaUplatu;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.narudzbenica = narudzbenica;
		this.tipOtpremnice = tipOtpremnice;
		this.obrisano = obrisano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojOtpremnice() {
		return brojOtpremnice;
	}

	public void setBrojOtpremnice(long brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}

	public Date getDatumOtpremnice() {
		return datumOtpremnice;
	}

	public void setDatumOtpremnice(Date datumOtpremnice) {
		this.datumOtpremnice = datumOtpremnice;
	}

	public double getIznosZaUplatu() {
		return iznosZaUplatu;
	}

	public void setIznosZaUplatu(double iznosZaUplatu) {
		this.iznosZaUplatu = iznosZaUplatu;
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

	public long getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(long narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public boolean isTipOtpremnice() {
		return tipOtpremnice;
	}

	public void setTipOtpremnice(boolean tipOtpremnice) {
		this.tipOtpremnice = tipOtpremnice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
	
}
