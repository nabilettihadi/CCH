package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import com.cch.cyclingmanager.service.ResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

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
}