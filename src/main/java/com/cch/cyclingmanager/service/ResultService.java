package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.embeddable.ResultId;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface ResultService {
    ResultDto save(ResultDto resultDto);
    Optional<ResultDto> findById(ResultId id);
    List<ResultDto> findAll();
    void delete(ResultDto resultDto);
    void deleteById(ResultId id);
    List<ResultDto> findByPhaseId(Long phaseId);
    List<ResultDto> findByCyclistId(Long cyclistId);
    ResultDto saveResult(Long cyclistId, Long phaseId, Duration time);
    List<ResultDto> getResultsForCompetition(Long competitionId);
    void calculateRankings(Long phaseId);
}