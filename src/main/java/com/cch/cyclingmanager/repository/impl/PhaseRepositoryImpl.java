package com.cch.cyclingmanager.repository.impl;

import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.repository.PhaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PhaseRepositoryImpl extends GenericRepositoryImpl<Phase, Long> implements PhaseRepository {
}