package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.EtudiantDto;
import com.exo2.Exercice2.dto.ProjetDto;
import com.exo2.Exercice2.mapper.EtudiantMapper;
import com.exo2.Exercice2.mapper.ProjetMapper;
import com.exo2.Exercice2.repository.ProjetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetService {
    private ProjetRepository projetRepository;
    private ProjetMapper projetMapper;
    private EtudiantMapper etudiantMapper;

    @Cacheable("projets")
    public Page<ProjetDto> findAll(Pageable pageable) {
        return  projetRepository.findAll(pageable).map(projetMapper::toDto);
    }

    public ProjetDto findById(Long id) {
        return projetMapper.toDto(projetRepository.findById(id).get());
    }

    @CachePut(value = "projets", key = "#projetDto.id")
    public ProjetDto save(ProjetDto projetDto) {
        return projetMapper.toDto(projetRepository.save(projetMapper.toEntity(projetDto)));
    }

    public List<EtudiantDto> findEtudiantsByProjetId(Long id)
    {
        return etudiantMapper.toDtos(projetRepository.findEtudiantsByProjetId(id));
    }

}
