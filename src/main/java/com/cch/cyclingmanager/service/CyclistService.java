package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.CyclistDto;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    CyclistDto save(CyclistDto cyclistDto);
    CyclistDto update(CyclistDto cyclistDto);
    Optional<CyclistDto> findById(Long id);
    List<CyclistDto> findAll();
    void delete(CyclistDto cyclistDto);
    void deleteById(Long id);
    List<CyclistDto> findByTeamId(Long teamId);
}