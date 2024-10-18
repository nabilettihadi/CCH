package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.repository.PhaseRepository;
import com.cch.cyclingmanager.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseRepository phaseRepository;

    @Override
    public Phase save(Phase phase) {
        return phaseRepository.save(phase);
    }

    @Override
    public Optional<Phase> findById(Long id) {
        return phaseRepository.findById(id);
    }

    @Override
    public List<Phase> findAll() {
        return phaseRepository.findAll();
    }

    @Override
    public void delete(Phase phase) {
        phaseRepository.delete(phase);
    }

    @Override
    public void deleteById(Long id) {
        phaseRepository.deleteById(id);
    }

    @Override
    public List<Phase> findByCompetitionId(Long competitionId) {
        // Cette méthode nécessite une implémentation dans le repository
        return phaseRepository.findByCompetitionId(competitionId);
    }
}