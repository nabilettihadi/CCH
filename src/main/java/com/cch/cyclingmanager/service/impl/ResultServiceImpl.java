package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.PhaseDto;
import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import com.cch.cyclingmanager.service.GeneralResultService;
import com.cch.cyclingmanager.service.PhaseService;
import com.cch.cyclingmanager.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResultDto create(ResultDto resultDto) {
        if (resultDto.getPhaseId() == null) {
            throw new IllegalArgumentException("PhaseId cannot be null");
        }

        Result result = modelMapper.map(resultDto, Result.class);

        Phase phase = phaseService.findById(resultDto.getPhaseId())
                .map(phaseDto -> modelMapper.map(phaseDto, Phase.class))
                .orElseThrow(() -> new EntityNotFoundException("Phase not found with id: " + resultDto.getPhaseId()));
        result.setPhase(phase);

        result = resultRepository.save(result);
        calculateRankings(resultDto.getPhaseId());
        return modelMapper.map(result, ResultDto.class);
    }

    @Override
    public ResultDto update(ResultDto resultDto) {
        if (resultDto.getPhaseId() == null || resultDto.getCyclistId() == null) {
            throw new IllegalArgumentException("Les IDs ne peuvent pas être nuls");
        }
        validateResult(resultDto);
        Result result = resultRepository.findById(new ResultId(resultDto.getPhaseId(), resultDto.getCyclistId()))
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        result = modelMapper.map(resultDto, Result.class);
        result = resultRepository.save(result);
        return modelMapper.map(result, ResultDto.class);
    }

    private void validateResult(ResultDto resultDto) {
        if (resultDto.getTime().isNegative()) {
            throw new IllegalArgumentException("Le temps ne peut pas être négatif");
        }
        if (resultDto.getRank() < 0) {
            throw new IllegalArgumentException("Le rang ne peut pas être négatif");
        }
    }
    @Override
    public Optional<ResultDto> findById(ResultId id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }
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
    public void deleteById(ResultId id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }
        resultRepository.deleteById(id);
    }

    @Override
    public List<ResultDto> findByPhaseId(Long phaseId) {
        if (phaseId == null) {
            throw new IllegalArgumentException("L'ID de la phase ne peut pas être null");
        }
        return resultRepository.findByPhaseId(phaseId).stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDto> findByCyclistId(Long cyclistId) {
        if (cyclistId == null) {
            throw new IllegalArgumentException("L'ID du cycliste ne peut pas être null");
        }
        return resultRepository.findByCyclistId(cyclistId).stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
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
            update(result);
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