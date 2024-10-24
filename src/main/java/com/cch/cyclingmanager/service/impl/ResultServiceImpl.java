package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.PhaseDto;
import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import com.cch.cyclingmanager.service.GeneralResultService;
import com.cch.cyclingmanager.service.PhaseService;
import com.cch.cyclingmanager.service.ResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private PhaseService phaseService;

    @Autowired
    private GeneralResultService generalResultService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResultDto save(ResultDto resultDto) {
        Result result = modelMapper.map(resultDto, Result.class);
        result = resultRepository.save(result);
        return modelMapper.map(result, ResultDto.class);
    }

    @Override
    public Optional<ResultDto> findById(ResultId id) {
        return resultRepository.findById(id)
                .map(result -> modelMapper.map(result, ResultDto.class));
    }

    @Override
    public List<ResultDto> findAll() {
        return resultRepository.findAll().stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ResultDto resultDto) {
        Result result = modelMapper.map(resultDto, Result.class);
        resultRepository.delete(result);
    }

    @Override
    public void deleteById(ResultId id) {
        resultRepository.deleteById(id);
    }

    @Override
    public List<ResultDto> findByPhaseId(Long phaseId) {
        return resultRepository.findByPhaseId(phaseId).stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDto> findByCyclistId(Long cyclistId) {
        return resultRepository.findByCyclistId(cyclistId).stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResultDto saveResult(Long cyclistId, Long phaseId, Duration time) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCyclistId(cyclistId);
        resultDto.setPhaseId(phaseId);
        resultDto.setTime(time);
        ResultDto savedResult = save(resultDto);
        calculateRankings(phaseId);
        return savedResult;
    }

    @Override
    public List<ResultDto> getResultsForCompetition(Long competitionId) {
        List<Long> phaseIds = phaseService.findByCompetitionId(competitionId)
                .stream()
                .map(PhaseDto::getId)
                .collect(Collectors.toList());
        return phaseIds.stream()
                .flatMap(phaseId -> findByPhaseId(phaseId).stream())
                .collect(Collectors.toList());
    }

    @Override
    public void calculateRankings(Long phaseId) {
        List<ResultDto> results = findByPhaseId(phaseId);
        results.sort(Comparator.comparing(ResultDto::getTime));
        for (int i = 0; i < results.size(); i++) {
            ResultDto result = results.get(i);
            result.setRank(i + 1);
            save(result);
        }
        updateGeneralResults(phaseId);
    }

    private void updateGeneralResults(Long phaseId) {
        PhaseDto phase = phaseService.findById(phaseId).orElseThrow(() -> new RuntimeException("Phase not found"));
        Long competitionId = phase.getCompetitionId();
        List<ResultDto> results = findByPhaseId(phaseId);
        for (ResultDto result : results) {
            generalResultService.updateGeneralResult(competitionId, result.getCyclistId(), result.getTime());
        }
    }
}