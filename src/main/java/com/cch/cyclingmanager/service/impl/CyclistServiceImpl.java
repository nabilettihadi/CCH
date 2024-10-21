package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.dto.CyclistDto;
import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.repository.CyclistRepository;
import com.cch.cyclingmanager.service.CyclistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CyclistServiceImpl implements CyclistService {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CyclistDto save(CyclistDto cyclistDto) {
        Cyclist cyclist = modelMapper.map(cyclistDto, Cyclist.class);
        cyclist = cyclistRepository.save(cyclist);
        return modelMapper.map(cyclist, CyclistDto.class);
    }

    @Override
    public CyclistDto update(CyclistDto cyclistDto) {
        if (cyclistDto.getId() == null) {
            throw new IllegalArgumentException("Cannot update a cyclist without an ID");
        }
        Cyclist cyclist = modelMapper.map(cyclistDto, Cyclist.class);
        cyclist = cyclistRepository.save(cyclist);
        return modelMapper.map(cyclist, CyclistDto.class);
    }

    @Override
    public Optional<CyclistDto> findById(Long id) {
        return cyclistRepository.findById(id)
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class));
    }

    @Override
    public List<CyclistDto> findAll() {
        return cyclistRepository.findAll().stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CyclistDto cyclistDto) {
        Cyclist cyclist = modelMapper.map(cyclistDto, Cyclist.class);
        cyclistRepository.delete(cyclist);
    }

    @Override
    public void deleteById(Long id) {
        cyclistRepository.deleteById(id);
    }

    @Override
    public List<CyclistDto> findByTeamId(Long teamId) {
        return cyclistRepository.findByTeamId(teamId).stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CyclistDto> findAllSortedByName() {
        return cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName", "firstName")).stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CyclistDto> findAllSortedByNationality() {
        return cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "nationality")).stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CyclistDto> findAllSortedByTeam() {
        return cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "team.name")).stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDto.class))
                .collect(Collectors.toList());
    }
}