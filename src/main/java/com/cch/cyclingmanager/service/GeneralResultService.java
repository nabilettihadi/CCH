package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;

import java.util.List;
import java.util.Optional;

public interface GeneralResultService {
    GeneralResultDto save(GeneralResultDto generalResultDto);
    Optional<GeneralResultDto> findById(GeneralResultId id);
    List<GeneralResultDto> findAll();
    void delete(GeneralResultDto generalResultDto);
    void deleteById(GeneralResultId id);
    List<GeneralResultDto> findByCompetitionId(Long competitionId);
    List<GeneralResultDto> findByCyclistId(Long cyclistId);
}