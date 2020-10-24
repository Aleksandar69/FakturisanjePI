package com.aleksandar.fakturisanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksandar.fakturisanje.model.Mjesto;

public interface MjestoRepository extends JpaRepository<Mjesto, Long> {

	Mjesto findByObrisanoAndId(boolean obrisano, long id);
	
	Page<Mjesto> findAllByNazivIgnoreCaseContainsAndObrisano(
			String naziv, boolean obrisano, Pageable pageable);
	
}
