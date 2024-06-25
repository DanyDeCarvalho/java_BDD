package com.exo2.Exercice2.controller;

import com.exo2.Exercice2.dto.EtudiantDto;
import com.exo2.Exercice2.dto.ProjetDto;
import com.exo2.Exercice2.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    @GetMapping
    public ResponseEntity<Page<ProjetDto>> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(projetService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(projetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjetDto> save(@RequestBody ProjetDto projetDto) {
        return ResponseEntity.ok(projetService.save(projetDto));
    }

    @GetMapping("/{id}/etudiants")
    public ResponseEntity<List<EtudiantDto>> findEtudiantsByProjetId(@PathVariable Long id) {
        return ResponseEntity.ok(projetService.findEtudiantsByProjetId(id));
    }
}
