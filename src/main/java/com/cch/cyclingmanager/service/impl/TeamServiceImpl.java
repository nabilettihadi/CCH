package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.TeamDto;
import com.cch.cyclingmanager.entity.Team;
import com.cch.cyclingmanager.repository.TeamRepository;
import com.cch.cyclingmanager.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TeamDto save(TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        team = teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public TeamDto update(TeamDto teamDto) {
        if (teamDto.getId() == null) {
            throw new IllegalArgumentException("Cannot update a team without an ID");
        }
        Team team = modelMapper.map(teamDto, Team.class);
        team = teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public Optional<TeamDto> findById(Long id) {
        return teamRepository.findById(id)
                .map(team -> modelMapper.map(team, TeamDto.class));
    }

    @Override
    public List<TeamDto> findAll() {
        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team, TeamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        teamRepository.delete(team);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<TeamDto> findByCountry(String country) {
        return teamRepository.findByCountry(country).stream()
                .map(team -> modelMapper.map(team, TeamDto.class))
                .collect(Collectors.toList());
    }
}