package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksandar.fakturisanje.model.Mjesto;

public interface MjestoRepo extends JpaRepository<Mjesto, Long> {

}
