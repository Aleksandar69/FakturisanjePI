package com.aleksandar.fakturisanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StavkaCjenovnika {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private float cijena;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "cjenovnik_id")
	private Cjenovnik cjenovnik;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="roba_usluga_id")
	private RobaUsluga robaUsluga;
	
	private boolean obrisano;
	
	
	public StavkaCjenovnika() {
		
	}

	public StavkaCjenovnika(float cijena, Cjenovnik cjenovnik, RobaUsluga robaUsluga) {
		super();
		this.cijena = cijena;
		this.cjenovnik = cjenovnik;
		this.robaUsluga = robaUsluga;
		this.obrisano = false;
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

	public Cjenovnik getCjenovnik() {
		return cjenovnik;
	}

	public void setCjenovnik(Cjenovnik cjenovnik) {
		this.cjenovnik = cjenovnik;
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
