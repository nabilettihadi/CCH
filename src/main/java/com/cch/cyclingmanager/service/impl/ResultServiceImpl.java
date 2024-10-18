package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import com.cch.cyclingmanager.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Optional<Result> findById(ResultId id) {
        return resultRepository.findById(id);
    }

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public void delete(Result result) {
        resultRepository.delete(result);
    }

    @Override
    public void deleteById(ResultId id) {
        resultRepository.deleteById(id);
    }

    @Override
    public List<Result> findByPhaseId(Long phaseId) {
        return resultRepository.findByPhaseId(phaseId);
    }

    @Override
    public List<Result> findByCyclistId(Long cyclistId) {
        return resultRepository.findByCyclistId(cyclistId);
    }
}