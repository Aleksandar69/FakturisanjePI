package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.PoslovnaGodina;

@Repository
public interface PoslovnagodinaRepo extends JpaRepository<PoslovnaGodina, Long> {

}
