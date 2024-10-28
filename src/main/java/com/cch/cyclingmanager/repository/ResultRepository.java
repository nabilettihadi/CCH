package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, ResultId> {
    List<Result> findByPhaseId(Long phaseId);

    List<Result> findByCyclistId(Long cyclistId);
}