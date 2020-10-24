package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Cjenovnik;

@Repository
public interface CjenovnikRepository extends JpaRepository<Cjenovnik, Long>{

	List<Cjenovnik> findAllByObrisano(boolean obrisano);
	
	Page<Cjenovnik> findAllByObrisano(boolean obrisano, Pageable pageable);

	Cjenovnik findByObrisanoAndId(boolean obrisano, long id);
	
	List<Cjenovnik> findByIdNot(long id);
	
}
