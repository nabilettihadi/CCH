package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PhaseDto {
    private Long id;
    private String name;
    private double distance;
    private LocalDate date;
    private Long competitionId;
}