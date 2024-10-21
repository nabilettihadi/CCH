package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByCountry(String country);
}