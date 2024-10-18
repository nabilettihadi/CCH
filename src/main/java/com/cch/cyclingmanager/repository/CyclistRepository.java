package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {
    List<Cyclist> findByTeamId(Long teamId);
}