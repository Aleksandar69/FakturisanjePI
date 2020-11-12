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
public class Preduzece {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naziv;
	
	private String adresa;
	
	private String PIB;
	
	private String tel;
	
	private String email;
	
	private String tekuciRacun;
	
	private String logo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mjesto_id")
	private Mjesto mjesto;
	
	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Otpremnica> optremnice = new HashSet<>();
    
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<>();
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();
	
	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Cjenovnik> cjenovnici = new HashSet<>();
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<GrupaRobe> grupeRobe = new HashSet<>();
	
	private boolean obrisano;

	public Preduzece(String naziv, String adresa, String pIB, String tel, String email, String tekuciRacun, String logo,
			Mjesto mjesto, Set<Faktura> fakture, Set<Otpremnica> optremnice, Set<Narudzbenica> narudzbenice,
			Set<PoslovniPartner> poslovniPartneri, Set<Cjenovnik> cjenovnici, Set<GrupaRobe> grupeRobe) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		PIB = pIB;
		this.tel = tel;
		this.email = email;
		this.tekuciRacun = tekuciRacun;
		this.logo = logo;
		this.mjesto = mjesto;
		this.fakture = fakture;
		this.optremnice = optremnice;
		this.narudzbenice = narudzbenice;
		this.poslovniPartneri = poslovniPartneri;
		this.cjenovnici = cjenovnici;
		this.grupeRobe = grupeRobe;
		this.obrisano = false;
	}

	public Preduzece() {
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public Set<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(Set<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	public Set<Cjenovnik> getCjenovnici() {
		return cjenovnici;
	}

	public void setCjenovnici(Set<Cjenovnik> cjenovnici) {
		this.cjenovnici = cjenovnici;
	}

	public Set<GrupaRobe> getGrupeRobe() {
		return grupeRobe;
	}

	public void setGrupeRobe(Set<GrupaRobe> grupeRobe) {
		this.grupeRobe = grupeRobe;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
	
	
	
}
