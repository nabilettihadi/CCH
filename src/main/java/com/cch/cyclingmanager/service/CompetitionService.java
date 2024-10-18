package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.entity.Competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Competition save(Competition competition);
    Competition update(Competition competition);
    Optional<Competition> findById(Long id);
    List<Competition> findAll();
    void delete(Competition competition);
    void deleteById(Long id);
    List<Competition> findByLocation(String location);
    List<Competition> findByDateRange(LocalDate startDate, LocalDate endDate);
}