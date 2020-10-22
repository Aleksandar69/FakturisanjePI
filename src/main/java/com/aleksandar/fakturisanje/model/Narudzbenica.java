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
public class Narudzbenica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long brojNarudzbenice;

    private Date datumNarudzbenice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preduzece_id")
    private Preduzece preduzece;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovni_partner_id")
    private PoslovniPartner poslovniPartner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovna_godina_id")
    private PoslovnaGodina poslovnaGodina;

    @OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
    private Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<>();

    @OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();

    @OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
    private Set<Otpremnica> otpremnice = new HashSet<>();

    private boolean tipNarudzbenice;

    private boolean obrisano;
    
    public Narudzbenica() {}

	public Narudzbenica(long brojNarudzbenice, Date datumNarudzbenice, Preduzece preduzece,
			PoslovniPartner poslovniPartner, PoslovnaGodina poslovnaGodina,
			Set<StavkaNarudzbenice> stavkeNarudzbenice, Set<Faktura> fakture, Set<Otpremnica> otpremnice,
			boolean tipNarudzbenice) {
		super();
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumNarudzbenice = datumNarudzbenice;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
		this.fakture = fakture;
		this.otpremnice = otpremnice;
		this.tipNarudzbenice = tipNarudzbenice;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojNarudzbenice() {
		return brojNarudzbenice;
	}

	public void setBrojNarudzbenice(long brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
	}

	public Date getDatumNarudzbenice() {
		return datumNarudzbenice;
	}

	public void setDatumNarudzbenice(Date datumNarudzbenice) {
		this.datumNarudzbenice = datumNarudzbenice;
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


	public Set<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(Set<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}

	public Set<Otpremnica> getOtpremnice() {
		return otpremnice;
	}

	public void setOtpremnice(Set<Otpremnica> otpremnice) {
		this.otpremnice = otpremnice;
	}

	public boolean isTipNarudzbenice() {
		return tipNarudzbenice;
	}

	public void setTipNarudzbenice(boolean tipNarudzbenice) {
		this.tipNarudzbenice = tipNarudzbenice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
    
	
    
}
