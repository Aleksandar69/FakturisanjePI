package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Preduzece;

@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece, Long>{

    List<Preduzece> findAllByObrisano(boolean obrisano);

    Preduzece findByObrisanoAndId(boolean obrisano, long id);

	
}
