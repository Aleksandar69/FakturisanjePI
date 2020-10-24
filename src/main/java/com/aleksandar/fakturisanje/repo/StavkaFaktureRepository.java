package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaFakture;

@Repository
public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture, Long>{

	List<StavkaFakture> findByFaktura_id(Long id);

	List<StavkaFakture> findAllByObrisano(boolean obrisano);

	StavkaFakture findByObrisanoAndId(boolean obrisano, long id);

	@Query("select s from StavkaFakture s group by s.robaUsluga.id")
	List<StavkaFakture> findDistinctStavkeFakture();
	
}