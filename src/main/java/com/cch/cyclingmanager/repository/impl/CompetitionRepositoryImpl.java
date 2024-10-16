package com.cch.cyclingmanager.repository.impl;

import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepositoryImpl extends GenericRepositoryImpl<Competition, Long> implements CompetitionRepository {
}