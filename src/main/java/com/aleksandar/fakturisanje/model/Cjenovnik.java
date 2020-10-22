package com.aleksandar.fakturisanje.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cjenovnik {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private Date datumVazenja;
	
	@OneToMany(mappedBy="cjenovnik", cascade = CascadeType.ALL)
	private Set<StavkaCjenovnika> stavkeCjenovnika = new HashSet<>();
	
	private boolean obrisano;
	
	
	public Cjenovnik() {
		
	}
	
	public Cjenovnik(Date datumVazenja, Set<StavkaCjenovnika> stavkeCjenovnika) {
		super();
		this.datumVazenja = datumVazenja;
		this.stavkeCjenovnika = stavkeCjenovnika;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public Set<StavkaCjenovnika> getStavkeCjenovnika() {
		return stavkeCjenovnika;
	}

	public void setStavkeCjenovnika(Set<StavkaCjenovnika> stavkeCjenovnika) {
		this.stavkeCjenovnika = stavkeCjenovnika;
	}

	public boolean isDeleted() {
		return obrisano;
	}

	public void setDeleted(boolean deleted) {
		this.obrisano = deleted;
	}
	
	
	
}
