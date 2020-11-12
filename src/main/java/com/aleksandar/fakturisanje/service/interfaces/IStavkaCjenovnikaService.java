package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.StavkaCjenovnika;

public interface IStavkaCjenovnikaService {

    List<StavkaCjenovnika> findAll();
    StavkaCjenovnika findOne(Long id);
    StavkaCjenovnika save(StavkaCjenovnika stavkaCenovnika);
    List<StavkaCjenovnika> findAllByRoba_usluga_id(Long id);
    Boolean delete(Long id);
	List<StavkaCjenovnika> findAllByCjenovnikId(long id);
}
