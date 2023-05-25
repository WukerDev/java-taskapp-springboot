package com.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.model.Zadanie;

public interface ZadanieService {
	Optional <Zadanie> getZadanie(Integer zadanie_id);

	Zadanie setZadanie(Zadanie zadanie);

	void deleteZadanie(Integer zadanie_id);
	Page<Zadanie> findZadaniaProjektu(Integer projektId, Pageable pageable);
}
