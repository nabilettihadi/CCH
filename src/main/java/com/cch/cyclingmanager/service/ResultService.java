package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    Result save(Result result);
    Optional<Result> findById(ResultId id);
    List<Result> findAll();
    void delete(Result result);
    void deleteById(ResultId id);
    List<Result> findByPhaseId(Long phaseId);
    List<Result> findByCyclistId(Long cyclistId);
}