package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import com.cch.cyclingmanager.service.CompetitionService;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CompetitionDto save(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDto.class);
    }

    @Override
    public CompetitionDto update(CompetitionDto competitionDto) {
        if (competitionDto.getId() == null) {
            throw new IllegalArgumentException("Cannot update a competition without an ID");
        }

        Competition existingCompetition = competitionRepository.findById(competitionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));

        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setPhases(existingCompetition.getPhases());
        competition.setGeneralResults(existingCompetition.getGeneralResults());

        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDto.class);
    }

    @Override
    public Optional<CompetitionDto> findById(Long id) {
        return competitionRepository.findById(id)
                .map(competition -> modelMapper.map(competition, CompetitionDto.class));
    }

    @Override
    public List<CompetitionDto> findAll() {
        return competitionRepository.findAll().stream()
                .map(competition -> modelMapper.map(competition, CompetitionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competitionRepository.delete(competition);
    }

    @Override
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public List<CompetitionDto> findByLocation(String location) {
        return competitionRepository.findByLocation(location).stream()
                .map(competition -> modelMapper.map(competition, CompetitionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompetitionDto> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return competitionRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(competition -> modelMapper.map(competition, CompetitionDto.class))
                .collect(Collectors.toList());
    }
}