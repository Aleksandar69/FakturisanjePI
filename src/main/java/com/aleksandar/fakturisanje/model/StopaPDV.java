package com.aleksandar.fakturisanje.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class StopaPDV {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public float procenat;
	
	private Date datumVazenja;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdv_id")
	private PDV pdv;
	
	@OneToMany(mappedBy = "stopapdva", cascade = CascadeType.ALL)
    private Set<GrupaRobe> grupeRobe = new HashSet<>();
	
	private boolean obrisano;
	
	public StopaPDV() {
		
	}

	public StopaPDV(float procenat, Date datumVazenja, PDV pdv) {
		super();
		this.procenat = procenat;
		this.datumVazenja = datumVazenja;
		this.pdv = pdv;
		this.obrisano = false;
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

	public PDV getPdv() {
		return pdv;
	}

	public void setPdv(PDV pdv) {
		this.pdv = pdv;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	public Set<GrupaRobe> getGrupeRobe() {
		return grupeRobe;
	}

	public void setGrupeRobe(Set<GrupaRobe> grupeRobe) {
		this.grupeRobe = grupeRobe;
	}
	
	
	
	
	
}
