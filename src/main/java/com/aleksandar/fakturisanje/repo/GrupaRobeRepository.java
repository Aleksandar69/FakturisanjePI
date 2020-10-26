package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksandar.fakturisanje.model.GrupaRobe;

public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long> {

	GrupaRobe findByObrisanoAndId(boolean obrisano, long id);
	List<GrupaRobe> findAllByPreduzece_idAndObrisano(Long id, boolean obrisano);
	Page<GrupaRobe> findAllByObrisanoAndNazivIgnoreCaseContains(boolean obrisano, String naziv, Pageable pageable);
	
}
