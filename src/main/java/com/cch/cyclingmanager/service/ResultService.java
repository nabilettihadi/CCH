package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.embeddable.ResultId;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface ResultService {
    ResultDto create(ResultDto resultDto);

    ResultDto update(ResultDto resultDto);

    Optional<ResultDto> findById(ResultId id);

    List<ResultDto> findAll();

    void deleteById(ResultId id);

    List<ResultDto> findByPhaseId(Long phaseId);

    List<ResultDto> findByCyclistId(Long cyclistId);

    List<ResultDto> getResultsForCompetition(Long competitionId);

    void calculateRankings(Long phaseId);
}