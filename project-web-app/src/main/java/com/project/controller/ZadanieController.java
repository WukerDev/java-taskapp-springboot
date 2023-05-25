package com.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.model.Zadanie;
import com.project.service.ZadanieService;

@RestController
@RequestMapping("/zadania")
public class ZadanieController {

    private ZadanieService zadanieService;

    @GetMapping("/{zadanie_id}")
    public ResponseEntity<Zadanie> getZadanie(@PathVariable Integer zadanie_id) {
        Optional<Zadanie> zadanie = zadanieService.getZadanie(zadanie_id);
        if (zadanie.isPresent()) {
            return ResponseEntity.ok(zadanie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Zadanie> setZadanie(@RequestBody Zadanie zadanie) {
        Zadanie createdZadanie = zadanieService.setZadanie(zadanie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZadanie);
    }

    @DeleteMapping("/{zadanie_id}")
    public ResponseEntity<Void> deleteZadanie(@PathVariable Integer zadanie_id) {
        zadanieService.deleteZadanie(zadanie_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projekt/{projektId}")
    public ResponseEntity<Page<Zadanie>> findZadaniaProjektu(@PathVariable Integer projektId, Pageable pageable) {
        Page<Zadanie> zadania = zadanieService.findZadaniaProjektu(projektId, pageable);
        if (zadania.hasContent()) {
            return ResponseEntity.ok(zadania);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
