package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    TeamDto create(TeamDto teamDto);

    TeamDto update(TeamDto teamDto);

    Optional<TeamDto> findById(Long id);

    List<TeamDto> findAll();

    void delete(TeamDto teamDto);

    void deleteById(Long id);

    List<TeamDto> findByCountry(String country);
}