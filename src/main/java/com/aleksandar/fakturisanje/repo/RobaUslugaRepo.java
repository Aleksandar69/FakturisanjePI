package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.RobaUsluga;

@Repository
public interface RobaUslugaRepo extends JpaRepository<RobaUsluga, Long>{

}
