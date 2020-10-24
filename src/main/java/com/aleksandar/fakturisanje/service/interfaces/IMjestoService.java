package com.aleksandar.fakturisanje.service.interfaces;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Mjesto;

public interface IMjestoService {

	Page<Mjesto> findAll(String naziv, int brStranice, int brPrikazanih);
	Mjesto findOne(Long id);
	Mjesto save (Mjesto mjesto);
	
}
