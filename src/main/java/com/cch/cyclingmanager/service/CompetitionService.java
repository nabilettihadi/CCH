package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.CompetitionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    CompetitionDto save(CompetitionDto competitionDto);
    CompetitionDto update(CompetitionDto competitionDto);
    Optional<CompetitionDto> findById(Long id);
    List<CompetitionDto> findAll();
    void delete(CompetitionDto competitionDto);
    void deleteById(Long id);
    List<CompetitionDto> findByLocation(String location);
    List<CompetitionDto> findByDateRange(LocalDate startDate, LocalDate endDate);
}