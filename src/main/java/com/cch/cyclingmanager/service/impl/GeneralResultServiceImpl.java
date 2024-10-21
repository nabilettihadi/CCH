package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import com.cch.cyclingmanager.repository.GeneralResultRepository;
import com.cch.cyclingmanager.service.GeneralResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneralResultServiceImpl implements GeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;

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
}