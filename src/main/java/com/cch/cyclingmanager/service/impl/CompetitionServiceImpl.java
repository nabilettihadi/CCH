package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import com.cch.cyclingmanager.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public void delete(Competition competition) {
        competitionRepository.delete(competition);
    }

    @Override
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public List<Competition> findByLocation(String location) {
        return competitionRepository.findByLocation(location);
    }

    @Override
    public List<Competition> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return competitionRepository.findByStartDateBetween(startDate, endDate);
    }
}