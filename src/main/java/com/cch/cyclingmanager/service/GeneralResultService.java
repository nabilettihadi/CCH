package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.CompetitionReportDto;
import com.cch.cyclingmanager.dto.CyclistPerformanceDto;
import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface GeneralResultService {
    GeneralResultDto save(GeneralResultDto generalResultDto);
    Optional<GeneralResultDto> findById(GeneralResultId id);
    List<GeneralResultDto> findAll();
    void delete(GeneralResultDto generalResultDto);
    void deleteById(GeneralResultId id);
    List<GeneralResultDto> findByCompetitionId(Long competitionId);
    List<GeneralResultDto> findByCyclistId(Long cyclistId);
    GeneralResultDto register(Long cyclistId, Long competitionId);
    void unregister(Long cyclistId, Long competitionId);
    List<GeneralResultDto> findRegisteredCyclistsByCompetitionId(Long competitionId);
    void updateGeneralResult(Long competitionId, Long cyclistId, Duration time);
    List<GeneralResultDto> getCompetitionRankings(Long competitionId);
    List<GeneralResultDto> getOverallRankings(List<Long> competitionIds);
    CompetitionReportDto generateCompetitionReport(Long competitionId);
    List<CyclistPerformanceDto> getCyclistPerformanceHistory(Long cyclistId);
}