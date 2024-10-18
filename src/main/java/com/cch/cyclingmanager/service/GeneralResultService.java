package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;

import java.util.List;
import java.util.Optional;

public interface GeneralResultService {
    GeneralResult save(GeneralResult generalResult);
    Optional<GeneralResult> findById(GeneralResultId id);
    List<GeneralResult> findAll();
    void delete(GeneralResult generalResult);
    void deleteById(GeneralResultId id);
    List<GeneralResult> findByCompetitionId(Long competitionId);
    List<GeneralResult> findByCyclistId(Long cyclistId);
}