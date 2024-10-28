package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.PhaseDto;

import java.util.List;
import java.util.Optional;

public interface PhaseService {
    PhaseDto save(PhaseDto phaseDto);

    Optional<PhaseDto> findById(Long id);

    List<PhaseDto> findAll();

    void delete(PhaseDto phaseDto);

    void deleteById(Long id);

    List<PhaseDto> findByCompetitionId(Long competitionId);
}