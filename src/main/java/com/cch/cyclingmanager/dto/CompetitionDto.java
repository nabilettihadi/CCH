package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CompetitionDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
}