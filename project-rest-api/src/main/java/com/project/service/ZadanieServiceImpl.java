package com.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Zadanie;
import com.project.repository.ZadanieRepository;

@Service
public class ZadanieServiceImpl implements ZadanieService {

    private ZadanieRepository zadanieRepository;

    @Autowired
    public ZadanieServiceImpl(ZadanieRepository zadanieRepository) {
        this.zadanieRepository = zadanieRepository;
    }

    @Override
    public Optional<Zadanie> getZadanie(Integer zadanie_id) {
        return zadanieRepository.findById(zadanie_id);
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        return zadanieRepository.save(zadanie);
    }

    @Override
    public void deleteZadanie(Integer zadanie_id) {
        zadanieRepository.deleteById(zadanie_id);
    }

    @Override
    public Page<Zadanie> findZadaniaProjektu(Integer projektId, Pageable pageable) {
        return zadanieRepository.findZadaniaProjektu(projektId, pageable);
    }
}
