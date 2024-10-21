package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.PhaseDto;
import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.repository.PhaseRepository;
import com.cch.cyclingmanager.service.PhaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PhaseDto save(PhaseDto phaseDto) {
        Phase phase = modelMapper.map(phaseDto, Phase.class);
        phase = phaseRepository.save(phase);
        return modelMapper.map(phase, PhaseDto.class);
    }

    @Override
    public Optional<PhaseDto> findById(Long id) {
        return phaseRepository.findById(id)
                .map(phase -> modelMapper.map(phase, PhaseDto.class));
    }

    @Override
    public List<PhaseDto> findAll() {
        return phaseRepository.findAll().stream()
                .map(phase -> modelMapper.map(phase, PhaseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(PhaseDto phaseDto) {
        Phase phase = modelMapper.map(phaseDto, Phase.class);
        phaseRepository.delete(phase);
    }

    @Override
    public void deleteById(Long id) {
        phaseRepository.deleteById(id);
    }

    @Override
    public List<PhaseDto> findByCompetitionId(Long competitionId) {
        return phaseRepository.findByCompetitionId(competitionId).stream()
                .map(phase -> modelMapper.map(phase, PhaseDto.class))
                .collect(Collectors.toList());
    }
}