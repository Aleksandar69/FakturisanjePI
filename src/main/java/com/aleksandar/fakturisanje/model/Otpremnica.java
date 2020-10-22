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
public class Otpremnica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long brojOtpremnice;
	
	private Date datumOtpremnice;
	
	private double iznosZaPlacanje;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preduzece_id")
    private Preduzece preduzece;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovni_partner_id")
    private PoslovniPartner poslovniPartner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovna_godina_id")
    private PoslovnaGodina poslovnaGodina;

    @OneToMany(mappedBy = "otpremnica", cascade = CascadeType.ALL)
    private Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();

    @OneToMany(mappedBy = "otpremnica", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "narudzbenica_id")
    private Narudzbenica narudzbenica;

    private boolean tipOtpremnice;

    private boolean obrisano;

    public Otpremnica() {}
    
	public Otpremnica(long brojOtpremnice, Date datumOtpremnice, double iznosZaPlacanje, Preduzece preduzece,
			PoslovniPartner poslovniPartner, PoslovnaGodina poslovnaGodina, Set<StavkaOtpremnice> stavkeOtpremnice,
			Set<Faktura> fakture, Narudzbenica narudzbenica, boolean tipOtpremnice) {
		super();
		this.brojOtpremnice = brojOtpremnice;
		this.datumOtpremnice = datumOtpremnice;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.stavkeOtpremnice = stavkeOtpremnice;
		this.fakture = fakture;
		this.narudzbenica = narudzbenica;
		this.tipOtpremnice = tipOtpremnice;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojOtpremnice() {
		return brojOtpremnice;
	}

	public void setBrojOtpremnice(long brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}

	public Date getDatumOtpremnice() {
		return datumOtpremnice;
	}

	public void setDatumOtpremnice(Date datumOtpremnice) {
		this.datumOtpremnice = datumOtpremnice;
	}

	public double getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public void setIznosZaPlacanje(double iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
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

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public Set<StavkaOtpremnice> getStavkeOtpremnice() {
		return stavkeOtpremnice;
	}

	public void setStavkeOtpremnice(Set<StavkaOtpremnice> stavkeOtpremnice) {
		this.stavkeOtpremnice = stavkeOtpremnice;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public boolean isTipOtpremnice() {
		return tipOtpremnice;
	}

	public void setTipOtpremnice(boolean tipOtpremnice) {
		this.tipOtpremnice = tipOtpremnice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
    
    

}
