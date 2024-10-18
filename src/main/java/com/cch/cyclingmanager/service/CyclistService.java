package com.cch.cyclingmanager.service;

import com.cch.cyclingmanager.entity.Cyclist;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    Cyclist save(Cyclist cyclist);
    Cyclist update(Cyclist cyclist);
    Optional<Cyclist> findById(Long id);
    List<Cyclist> findAll();
    void delete(Cyclist cyclist);
    void deleteById(Long id);
    List<Cyclist> findByTeamId(Long teamId);
}