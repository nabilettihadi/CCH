package com.cch.cyclingmanager.service.impl;

import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.repository.CyclistRepository;
import com.cch.cyclingmanager.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CyclistServiceImpl implements CyclistService {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Override
    public Cyclist save(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Optional<Cyclist> findById(Long id) {
        return cyclistRepository.findById(id);
    }

    @Override
    public List<Cyclist> findAll() {
        return cyclistRepository.findAll();
    }

    @Override
    public void delete(Cyclist cyclist) {
        cyclistRepository.delete(cyclist);
    }

    @Override
    public void deleteById(Long id) {
        cyclistRepository.deleteById(id);
    }

    @Override
    public List<Cyclist> findByTeamId(Long teamId) {
        // Cette méthode nécessite une implémentation dans le repository
        return cyclistRepository.findByTeamId(teamId);
    }
}