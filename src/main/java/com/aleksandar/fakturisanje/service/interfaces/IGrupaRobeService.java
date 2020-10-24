package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.GrupaRobe;

public interface IGrupaRobeService {
	
	Page<GrupaRobe> finadAll(String naziv, int brStranice, int brPrikazanih);
	GrupaRobe findOne(Long id);
	List<GrupaRobe> findByPreduzece_id(Long id);
	GrupaRobe save(GrupaRobe grupaRobe);
	Boolean delete(Long id);
}
