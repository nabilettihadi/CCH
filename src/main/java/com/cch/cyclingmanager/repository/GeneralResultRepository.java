package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {
    List<GeneralResult> findByIdCompetitionId(Long competitionId);

    List<GeneralResult> findByIdCyclistId(Long cyclistId);
}