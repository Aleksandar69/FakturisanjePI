package com.aleksandar.fakturisanje.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class PoslovnaGodina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @Min(2000)
    @Max(3000)
    private int godina;
    
    private boolean zakljucana;
    
    @OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();
    
    @OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
    private Set<Otpremnica> otpremnice = new HashSet<>();
    
    @OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
    
    private Set<Narudzbenica> narudzbenice = new HashSet<>();
    
    private boolean obrisano;
    
    public PoslovnaGodina() {}

	public PoslovnaGodina(int godina, boolean zakljucana, Set<Faktura> fakture, Set<Otpremnica> otpremnice,
			Set<Narudzbenica> narudzbenice) {
		super();
		this.godina = godina;
		this.zakljucana = zakljucana;
		this.fakture = fakture;
		this.otpremnice = otpremnice;
		this.narudzbenice = narudzbenice;
		this.obrisano = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public boolean isZakljucana() {
		return zakljucana;
	}

	public void setZakljucana(boolean zakljucana) {
		this.zakljucana = zakljucana;
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

	public Set<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}

	public void setNarudzbenice(Set<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
    
    
    
    
}
