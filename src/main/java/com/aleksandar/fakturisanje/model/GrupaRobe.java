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
public class GrupaRobe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naziv;
	
	@OneToMany(mappedBy="grupaRobe", cascade = CascadeType.ALL)
	private Set<RobaUsluga> robaUsluge = new HashSet<>();
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="preduzece_id")
	private Preduzece preduzece;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdv_id")
	private PDV pdv;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stopapdv_id")
	private StopaPDV stopapdva;
	
	private boolean obrisano;

	public GrupaRobe(String naziv, Set<RobaUsluga> robaUsluge, Preduzece preduzece, PDV pdv) {
		super();
		this.naziv = naziv;
		this.robaUsluge = robaUsluge;
		this.preduzece = preduzece;
		this.pdv = pdv;
		this.obrisano = false;
	}

	public GrupaRobe() {
		// TODO Auto-generated constructor stub
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

	public Set<RobaUsluga> getRobaUsluge() {
		return robaUsluge;
	}

	public void setRobaUsluge(Set<RobaUsluga> robaUsluge) {
		this.robaUsluge = robaUsluge;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
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

	public StopaPDV getStopapdva() {
		return stopapdva;
	}

	public void setStopapdva(StopaPDV stopapdva) {
		this.stopapdva = stopapdva;
	}
	
	

}
