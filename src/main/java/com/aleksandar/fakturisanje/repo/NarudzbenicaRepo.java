package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Narudzbenica;

@Repository
public interface NarudzbenicaRepo extends JpaRepository<Narudzbenica, Long>{

}
