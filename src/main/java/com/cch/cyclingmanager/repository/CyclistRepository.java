package com.cch.cyclingmanager.repository;

import com.cch.cyclingmanager.entity.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {
}