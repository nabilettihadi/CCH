package com.cch.cyclingmanager.repository.impl;

import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.repository.CyclistRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CyclistRepositoryImpl extends GenericRepositoryImpl<Cyclist, Long> implements CyclistRepository {
}