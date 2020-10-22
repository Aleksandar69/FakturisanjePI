package com.aleksandar.fakturisanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StavkaOtpremnice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String jedinicaMjere;
	
	private int kolicina;
	
	private float cijena;
	
	private float iznos;
	
	private String opis;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roba_usluga_id")
    private RobaUsluga robaUsluga;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "otpremnica_id")
    private Otpremnica otpremnica;

    private boolean obrisano;
    
    public StavkaOtpremnice() {
    	
    }

	public StavkaOtpremnice(String jedinicaMjere, int kolicina, float cijena, float iznos, String opis,
			RobaUsluga robaUsluga, Otpremnica otpremnica) {
		super();
		this.jedinicaMjere = jedinicaMjere;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.iznos = iznos;
		this.opis = opis;
		this.robaUsluga = robaUsluga;
		this.otpremnica = otpremnica;
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

	public float getCijena() {
		return cijena;
	}

	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
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

	public Otpremnica getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(Otpremnica otpremnica) {
		this.otpremnica = otpremnica;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
    
    
	
}
