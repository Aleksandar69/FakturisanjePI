package com.aleksandar.fakturisanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StavkaFakture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long kolicina;
	
	private float cijena;
	
	private double rabat;
	
	private double pdvOsnovica;
	
	private double pdvProcenat;
	
	private double iznosPdva;
	
	private double iznos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "faktura_id")
	private Faktura faktura;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roba_usluga_id")
	private RobaUsluga robaUsluga;
	
	private boolean obrisano;

	public StavkaFakture() {}

	public StavkaFakture(long kolicina, float cijena, double rabat, double pdvOsnovica, double pdvProcenat,
			double iznosPdva, double iznos, Faktura faktura) {
		super();
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.rabat = rabat;
		this.pdvOsnovica = pdvOsnovica;
		this.pdvProcenat = pdvProcenat;
		this.iznosPdva = iznosPdva;
		this.iznos = iznos;
		this.faktura = faktura;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
	}

	public float getCijena() {
		return cijena;
	}

	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getPdvOsnovica() {
		return pdvOsnovica;
	}

	public void setPdvOsnovica(double pdvOsnovica) {
		this.pdvOsnovica = pdvOsnovica;
	}

	public double getPdvProcenat() {
		return pdvProcenat;
	}

	public void setPdvProcenat(double pdvProcenat) {
		this.pdvProcenat = pdvProcenat;
	}

	public double getIznosPdva() {
		return iznosPdva;
	}

	public void setIznosPdva(double iznosPdva) {
		this.iznosPdva = iznosPdva;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public Faktura getFaktura() {
		return faktura;
	}

	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	
	
}
