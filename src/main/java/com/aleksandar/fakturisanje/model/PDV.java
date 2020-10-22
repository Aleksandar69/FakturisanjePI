package com.aleksandar.fakturisanje.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PDV {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naziv;
	
	@OneToMany(mappedBy = "pdv", cascade = CascadeType.ALL)
	private Set<StopaPDV> stopePdva = new HashSet<>();
	
	@OneToMany(mappedBy = "pdv", cascade = CascadeType.ALL)
	private Set<GrupaRobe> grupaRObe = new HashSet<>();
	
	private boolean obrisano;
	
	public PDV() {}
	
	

	public PDV(String naziv, Set<StopaPDV> stopePdva, Set<GrupaRobe> grupaRObe) {
		super();
		this.naziv = naziv;
		this.stopePdva = stopePdva;
		this.grupaRObe = grupaRObe;
		this.obrisano = false;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<StopaPDV> getStopePdva() {
		return stopePdva;
	}

	public void setStopePdva(Set<StopaPDV> stopePdva) {
		this.stopePdva = stopePdva;
	}

	public Set<GrupaRobe> getGrupaRObe() {
		return grupaRObe;
	}

	public void setGrupaRObe(Set<GrupaRobe> grupaRObe) {
		this.grupaRObe = grupaRObe;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
}
