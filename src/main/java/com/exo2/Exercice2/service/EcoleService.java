package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.EcoleDto;
import com.exo2.Exercice2.entity.Ecole;
import com.exo2.Exercice2.entity.Etudiant;
import com.exo2.Exercice2.mapper.EcoleMapper;
import com.exo2.Exercice2.repository.EcoleRepository;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EcoleService {
    private EcoleRepository ecoleRepository;
    private EcoleMapper ecoleMapper;

    @Cacheable("ecoles")
    public Page<EcoleDto> findAll(Pageable pageable) {
        return ecoleRepository.findAll(pageable).map(ecoleMapper::toDto);
    }

    public EcoleDto findById(long id) {
        return ecoleMapper.toDto(ecoleRepository.findById(id).orElse(null));
    }

    public List<EcoleDto> findByNomEtudiant(String nomEtudiant) {
        return ecoleMapper.toDtos(ecoleRepository.findEcolesFromNomEtudiant(nomEtudiant));
    }

    @CachePut(value = "ecoles", key = "#ecoleDto.id")
    public EcoleDto save(EcoleDto ecoleDto) {
        Ecole ecole = ecoleMapper.toEntity(ecoleDto);
        ecole.getEtudiants().stream().forEach(e -> e.setEcole(ecole));
        return ecoleMapper.toDto(ecoleRepository.save(ecole));
    }
}
