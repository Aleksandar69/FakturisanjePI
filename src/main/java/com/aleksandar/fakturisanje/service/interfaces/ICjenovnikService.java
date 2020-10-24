package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;


public interface ICjenovnikService {

	Page<Cjenovnik> findAll(int brStranice, int brPrikazanih);
	Page<StavkaCjenovnika> findAllByCjenovnikId(long id, String nazivRobeUsluge, int brStranice, int brPrikazanih);
	List<Cjenovnik> findAllById(long id);
	Cjenovnik findOne(Long id);
	Cjenovnik save(Cjenovnik cjenovnik);
	Boolean delete(long id);
	
	void kopirajCjenovnik(Cjenovnik ciljani, Cjenovnik izvorni);
	
}
