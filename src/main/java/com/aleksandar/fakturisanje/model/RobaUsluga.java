package com.aleksandar.fakturisanje.model;

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
public class RobaUsluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String naziv;
	
	private String jedinicaMjere;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="grupa_robe_id")
	private GrupaRobe grupaRobe;
	
	@OneToMany(mappedBy = "robaUsluga", cascade= CascadeType.ALL)
	private Set<StavkaFakture> stavkeFakture = new HashSet<>();
	
	@OneToMany(mappedBy = "robaUsluga", cascade= CascadeType.ALL)
	private Set<StavkaCjenovnika> stavkeCjenovnika = new HashSet<>();
	
	
	
	private boolean obrisano;
	
	public RobaUsluga() {}

	public RobaUsluga(String naziv, String jedinicaMjere, Set<StavkaFakture> stavkeFakture,
			Set<StavkaCjenovnika> stavkeCjenovnika, GrupaRobe grupaRobe) {
		super();
		this.naziv = naziv;
		this.jedinicaMjere = jedinicaMjere;
		this.stavkeFakture = stavkeFakture;
		this.stavkeCjenovnika = stavkeCjenovnika;
		this.grupaRobe = grupaRobe;
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

	public String getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(String jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	public Set<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(Set<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}

	public Set<StavkaCjenovnika> getStavkeCjenovnika() {
		return stavkeCjenovnika;
	}

	public void setStavkeCjenovnika(Set<StavkaCjenovnika> stavkeCjenovnika) {
		this.stavkeCjenovnika = stavkeCjenovnika;
	}

	public GrupaRobe getGrupaRobe() {
		return grupaRobe;
	}

	public void setGrupaRobe(GrupaRobe grupaRobe) {
		this.grupaRobe = grupaRobe;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
	
}
