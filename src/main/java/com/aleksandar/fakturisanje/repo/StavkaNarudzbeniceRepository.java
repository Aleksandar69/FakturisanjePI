package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;

@Repository
public interface StavkaNarudzbeniceRepository extends JpaRepository<StavkaNarudzbenice, Long>{
	
	  List<StavkaNarudzbenice> findByNarudzbenica_id(Long id);

	  List<StavkaNarudzbenice> findAllByObrisano(boolean obrisano);

	  StavkaNarudzbenice findByObrisanoAndId(boolean obrisano, long id);

}
