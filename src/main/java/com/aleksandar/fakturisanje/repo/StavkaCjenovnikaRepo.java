package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaCjenovnika;

@Repository
public interface StavkaCjenovnikaRepo extends JpaRepository<StavkaCjenovnika, Long>{

}
