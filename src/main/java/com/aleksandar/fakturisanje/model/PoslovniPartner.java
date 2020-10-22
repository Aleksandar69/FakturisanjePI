package com.aleksandar.fakturisanje.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class PoslovniPartner {

	private long id;
	
	private String naziv;
	
	private String adresa;
	
	@Min(0)
	@Max(1)
	private int vrstaPartnera; //0 kupac 1 prodavac
	
	private String tekuciRacun;
	
	private String PIB;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "preduzece_id")
	private Preduzece preduzece;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mjesto_id")
	public Mjesto mjesto;
	
	@OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();
	
    @OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Otpremnica> optremnice = new HashSet<>();
    
    @OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<>();
    
	@OneToMany( cascade = CascadeType.ALL)
	private Set<Cjenovnik> cjenovnici = new HashSet<>();
	
	private boolean obrisano;
	
	public PoslovniPartner() {}

	public PoslovniPartner(String naziv, String adresa, int vrstaPartnera, String tekuciRacun, String pIB,
			Preduzece preduzece, Mjesto mjesto, Set<Faktura> fakture, Set<Otpremnica> optremnice,
			Set<Narudzbenica> narudzbenice, Set<Cjenovnik> cjenovnici) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.vrstaPartnera = vrstaPartnera;
		this.tekuciRacun = tekuciRacun;
		PIB = pIB;
		this.preduzece = preduzece;
		this.mjesto = mjesto;
		this.fakture = fakture;
		this.optremnice = optremnice;
		this.narudzbenice = narudzbenice;
		this.cjenovnici = cjenovnici;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getVrstaPartnera() {
		return vrstaPartnera;
	}

	public void setVrstaPartnera(int vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public Mjesto getMjesto() {
		return mjesto;
	}

	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}

	public Set<Otpremnica> getOptremnice() {
		return optremnice;
	}

	public void setOptremnice(Set<Otpremnica> optremnice) {
		this.optremnice = optremnice;
	}

	public Set<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}

	public void setNarudzbenice(Set<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}

	public Set<Cjenovnik> getCjenovnici() {
		return cjenovnici;
	}

	public void setCjenovnici(Set<Cjenovnik> cjenovnici) {
		this.cjenovnici = cjenovnici;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
	

	
}
