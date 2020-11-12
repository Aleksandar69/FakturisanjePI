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
public class Cjenovnik {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private Date datumVazenjaOd;
	
	private Date datumVazenjaDo;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "poslovnipartner_id")
	private PoslovniPartner poslovniPartner;
	
	
	@OneToMany(mappedBy="cjenovnik", cascade = CascadeType.ALL)
	private Set<StavkaCjenovnika> stavkeCjenovnika = new HashSet<>();
	
	
	private boolean obrisano;
	
	
	public Cjenovnik() {
		
	}
	
	public Cjenovnik(Date datumVazenja, Set<StavkaCjenovnika> stavkeCjenovnika) {
		super();
		this.datumVazenjaOd = datumVazenja;
		this.stavkeCjenovnika = stavkeCjenovnika;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenjaOd() {
		return datumVazenjaOd;
	}

	public void setDatumVazenjaOd(Date datumVazenja) {
		this.datumVazenjaOd = datumVazenja;
	}

	public Set<StavkaCjenovnika> getStavkeCjenovnika() {
		return stavkeCjenovnika;
	}

	public void setStavkeCjenovnika(Set<StavkaCjenovnika> stavkeCjenovnika) {
		this.stavkeCjenovnika = stavkeCjenovnika;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	public Date getDatumVazenjaDo() {
		return datumVazenjaDo;
	}

	public void setDatumVazenjaDo(Date datumVazenjaDo) {
		this.datumVazenjaDo = datumVazenjaDo;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}
	
	
	
}
