package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import com.cch.cyclingmanager.repository.GeneralResultRepository;
import com.cch.cyclingmanager.service.CompetitionService;
import com.cch.cyclingmanager.service.CyclistService;
import com.cch.cyclingmanager.service.GeneralResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.dto.CyclistDto;
import com.cch.cyclingmanager.dto.CompetitionReportDto;
import com.cch.cyclingmanager.dto.CyclistPerformanceDto;

@Service
@Transactional
public class GeneralResultServiceImpl implements GeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CyclistService cyclistService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GeneralResultDto save(GeneralResultDto generalResultDto) {
        GeneralResult generalResult = modelMapper.map(generalResultDto, GeneralResult.class);
        generalResult = generalResultRepository.save(generalResult);
        return modelMapper.map(generalResult, GeneralResultDto.class);
    }

    @Override
    public Optional<GeneralResultDto> findById(GeneralResultId id) {
        return generalResultRepository.findById(id)
                .map(generalResult -> modelMapper.map(generalResult, GeneralResultDto.class));
    }

    @Override
    public List<GeneralResultDto> findAll() {
        return generalResultRepository.findAll().stream()
                .map(generalResult -> modelMapper.map(generalResult, GeneralResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(GeneralResultDto generalResultDto) {
        GeneralResult generalResult = modelMapper.map(generalResultDto, GeneralResult.class);
        generalResultRepository.delete(generalResult);
    }

    @Override
    public void deleteById(GeneralResultId id) {
        generalResultRepository.deleteById(id);
    }

    @Override
    public List<GeneralResultDto> findByCompetitionId(Long competitionId) {
        return generalResultRepository.findByIdCompetitionId(competitionId).stream()
                .map(generalResult -> modelMapper.map(generalResult, GeneralResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralResultDto> findByCyclistId(Long cyclistId) {
        return generalResultRepository.findByIdCyclistId(cyclistId).stream()
                .map(generalResult -> modelMapper.map(generalResult, GeneralResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GeneralResultDto register(Long cyclistId, Long competitionId) {
        GeneralResultDto generalResultDto = new GeneralResultDto();
        generalResultDto.setCyclistId(cyclistId);
        generalResultDto.setCompetitionId(competitionId);
        generalResultDto.setRank(0);
        generalResultDto.setTotalTime(Duration.ZERO);
        return save(generalResultDto);
    }

    @Override
    public void unregister(Long cyclistId, Long competitionId) {
        GeneralResultId id = new GeneralResultId(competitionId, cyclistId);
        deleteById(id);
    }

    @Override
    public List<GeneralResultDto> findRegisteredCyclistsByCompetitionId(Long competitionId) {
        return findByCompetitionId(competitionId);
    }

    @Override
    public void updateGeneralResult(Long competitionId, Long cyclistId, Duration time) {
        GeneralResultId id = new GeneralResultId(competitionId, cyclistId);
        GeneralResultDto generalResult = findById(id).orElse(new GeneralResultDto(competitionId, cyclistId, 0, Duration.ZERO));
        generalResult.setTotalTime(generalResult.getTotalTime().plus(time));
        save(generalResult);
        updateRankings(competitionId);
    }

    @Override
    public List<GeneralResultDto> getCompetitionRankings(Long competitionId) {
        List<GeneralResultDto> results = findByCompetitionId(competitionId);
        results.sort(Comparator.comparing(GeneralResultDto::getTotalTime));
        for (int i = 0; i < results.size(); i++) {
            results.get(i).setRank(i + 1);
        }
        return results;
    }

    @Override
    public List<GeneralResultDto> getOverallRankings(List<Long> competitionIds) {
        Map<Long, Duration> cyclistTotalTimes = new HashMap<>();
        for (Long competitionId : competitionIds) {
            List<GeneralResultDto> results = findByCompetitionId(competitionId);
            for (GeneralResultDto result : results) {
                cyclistTotalTimes.merge(result.getCyclistId(), result.getTotalTime(), Duration::plus);
            }
        }
        List<GeneralResultDto> overallResults = cyclistTotalTimes.entrySet().stream()
                .map(entry -> new GeneralResultDto(null, entry.getKey(), 0, entry.getValue()))
                .collect(Collectors.toList());
        overallResults.sort(Comparator.comparing(GeneralResultDto::getTotalTime));
        for (int i = 0; i < overallResults.size(); i++) {
            overallResults.get(i).setRank(i + 1);
        }
        return overallResults;
    }

    @Override
    public CompetitionReportDto generateCompetitionReport(Long competitionId) {
        CompetitionDto competition = competitionService.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        List<GeneralResultDto> rankings = getCompetitionRankings(competitionId);
        List<CyclistDto> participants = rankings.stream()
                .map(result -> cyclistService.findById(result.getCyclistId())
                        .orElseThrow(() -> new RuntimeException("Cyclist not found")))
                .collect(Collectors.toList());
        return new CompetitionReportDto(competition, participants, rankings);
    }

    @Override
    public List<CyclistPerformanceDto> getCyclistPerformanceHistory(Long cyclistId) {
        List<GeneralResultDto> results = findByCyclistId(cyclistId);
        return results.stream()
                .map(result -> {
                    CompetitionDto competition = competitionService.findById(result.getCompetitionId())
                            .orElseThrow(() -> new RuntimeException("Competition not found"));
                    return new CyclistPerformanceDto(competition, result.getRank(), result.getTotalTime());
                })
                .collect(Collectors.toList());
    }

    private void updateRankings(Long competitionId) {
        List<GeneralResultDto> results = findByCompetitionId(competitionId);
        results.sort(Comparator.comparing(GeneralResultDto::getTotalTime));
        for (int i = 0; i < results.size(); i++) {
            GeneralResultDto result = results.get(i);
            result.setRank(i + 1);
            save(result);
        }
    }
}