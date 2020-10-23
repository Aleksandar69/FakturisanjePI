package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StopaPDV;


@Repository
public interface StopaPdvRepo extends JpaRepository<StopaPDV, Long>{

}
