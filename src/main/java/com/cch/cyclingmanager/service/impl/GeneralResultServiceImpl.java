package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import com.cch.cyclingmanager.repository.GeneralResultRepository;
import com.cch.cyclingmanager.service.GeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GeneralResultServiceImpl implements GeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Override
    public GeneralResult save(GeneralResult generalResult) {
        return generalResultRepository.save(generalResult);
    }

    @Override
    public Optional<GeneralResult> findById(GeneralResultId id) {
        return generalResultRepository.findById(id);
    }

    @Override
    public List<GeneralResult> findAll() {
        return generalResultRepository.findAll();
    }

    @Override
    public void delete(GeneralResult generalResult) {
        generalResultRepository.delete(generalResult);
    }

    @Override
    public void deleteById(GeneralResultId id) {
        generalResultRepository.deleteById(id);
    }

    @Override
    public List<GeneralResult> findByCompetitionId(Long competitionId) {
        return generalResultRepository.findByIdCompetitionId(competitionId);
    }

    @Override
    public List<GeneralResult> findByCyclistId(Long cyclistId) {
        return generalResultRepository.findByIdCyclistId(cyclistId);
    }
}