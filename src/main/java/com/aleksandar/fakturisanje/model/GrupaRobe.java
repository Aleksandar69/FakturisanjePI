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
public class GrupaRobe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String grupaNaziv;
	
	@OneToMany(mappedBy="grupaRobe", cascade = CascadeType.ALL)
	private Set<RobaUsluga> robaUsluge = new HashSet<>();
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="preduzece_id")
	private Preduzece preduzece;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdv_id")
	private PDV pdv;
	
	private boolean obrisano;

	public GrupaRobe(String grupaNaziv, Set<RobaUsluga> robaUsluge, Preduzece preduzece, PDV pdv) {
		super();
		this.grupaNaziv = grupaNaziv;
		this.robaUsluge = robaUsluge;
		this.preduzece = preduzece;
		this.pdv = pdv;
		this.obrisano = false;
	}
	
	

}
