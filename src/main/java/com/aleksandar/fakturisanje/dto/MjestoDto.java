package com.aleksandar.fakturisanje.dto;

public class MjestoDto {

	private long id;
	private String naziv;
	private int postanskiBroj;
	private String drzava;
	
	public MjestoDto() {}
	
	public MjestoDto(long id, String naziv, int postanskiBroj, String drzava) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;
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

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	
	
	
}
