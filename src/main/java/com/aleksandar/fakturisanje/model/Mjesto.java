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
public class Mjesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naziv;
	
	private int postanskiBroj;
	
	private String drzava;
	
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();
    
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private Set<Preduzece> preduzeca = new HashSet<>();
	
	private boolean obrisano;

	public Mjesto() {}
	
	public Mjesto(String naziv, int postanskiBroj, String drzava, Set<PoslovniPartner> poslovniPartneri,
			Set<Preduzece> preduzeca) {
		super();
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;
		this.poslovniPartneri = poslovniPartneri;
		this.preduzeca = preduzeca;
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

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Set<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(Set<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	public Set<Preduzece> getPreduzeca() {
		return preduzeca;
	}

	public void setPreduzeca(Set<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	

}
