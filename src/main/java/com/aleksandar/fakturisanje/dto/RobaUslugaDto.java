package com.aleksandar.fakturisanje.dto;

public class RobaUslugaDto {

	private Long id;
	private String nazivRobeUsluge;
	private String jedinicaMjere;
	private Long grupaRobe;
	
	public RobaUslugaDto() {}

	public RobaUslugaDto(Long id, String nazivRobeUsluge, String jedinicaMjere, Long grupaRobe) {
		super();
		this.id = id;
		this.nazivRobeUsluge = nazivRobeUsluge;
		this.jedinicaMjere = jedinicaMjere;
		this.grupaRobe = grupaRobe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivRobeUsluge() {
		return nazivRobeUsluge;
	}

	public void setNazivRobeUsluge(String nazivRobeUsluge) {
		this.nazivRobeUsluge = nazivRobeUsluge;
	}

	public String getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(String jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	public Long getGrupaRobe() {
		return grupaRobe;
	}

	public void setGrupaRobe(Long grupaRobe) {
		this.grupaRobe = grupaRobe;
	}
	
	
}
