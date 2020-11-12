package com.aleksandar.fakturisanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StavkaNarudzbenice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String jedinicaMjere;
	
	private int kolicina;
	
	private String opis;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "roba_usluga_id")
    private RobaUsluga robaUsluga;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "narudzbenica_id")
    private Narudzbenica narudzbenica;

    private boolean obrisano;

    public StavkaNarudzbenice() {
	}

	public StavkaNarudzbenice(String jedinicaMjere, int kolicina, String opis, RobaUsluga robaUsluga,
			Narudzbenica narudzbenica) {
		super();
		this.jedinicaMjere = jedinicaMjere;
		this.kolicina = kolicina;
		this.opis = opis;
		this.robaUsluga = robaUsluga;
		this.narudzbenica = narudzbenica;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(String jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
    
}
