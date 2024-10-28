package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findByLocation(String location);

    List<Competition> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}