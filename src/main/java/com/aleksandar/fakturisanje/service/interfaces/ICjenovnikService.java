package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;


public interface ICjenovnikService {

	Page<Cjenovnik> findAll(int brStranice, int brPrikazanih);
	List<Cjenovnik> findAllC();
	Page<StavkaCjenovnika> findAllByCjenovnikId(long id, String nazivRobeUsluge, int brStranice, int brPrikazanih);
	List<Cjenovnik> findAllByNotId(long id);
	Cjenovnik findOne(Long id);
	Cjenovnik save(Cjenovnik cjenovnik);
	Boolean delete(long id);
	
	List<Cjenovnik> findAllByPreduzeceId(long id);
	List<Cjenovnik> findAllByPoslParnterId(long id);
	
	void kopirajCjenovnik(Cjenovnik ciljani, Cjenovnik izvorni);
	
}
