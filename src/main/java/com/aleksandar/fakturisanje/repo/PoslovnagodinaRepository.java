package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.PoslovnaGodina;

@Repository
public interface PoslovnagodinaRepository extends JpaRepository<PoslovnaGodina, Long> {
	
	PoslovnaGodina findByObrisanoAndId(boolean obrisano, long id);
	
	List<PoslovnaGodina> findAllByObrisano(boolean obrisano);

	List<PoslovnaGodina> findByZakljucanaIsFalseAndObrisanoIsFalse();
}
