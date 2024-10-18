package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.entity.Phase;

import java.util.List;
import java.util.Optional;

public interface PhaseService {
    Phase save(Phase phase);
    Optional<Phase> findById(Long id);
    List<Phase> findAll();
    void delete(Phase phase);
    void deleteById(Long id);
    List<Phase> findByCompetitionId(Long competitionId);
}