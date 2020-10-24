package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.PDV;

@Repository
public interface PdvRepository extends JpaRepository<PDV, Long>{

	PDV findByObrisanoAndId(boolean obrisano, long id);
	
	List<PDV> findAllByObrisano(boolean obrisano);
	
	
}
