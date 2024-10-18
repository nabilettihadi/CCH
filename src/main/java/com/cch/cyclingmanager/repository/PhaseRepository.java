package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    List<Phase> findByCompetitionId(Long competitionId);
}