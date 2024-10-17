package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
}