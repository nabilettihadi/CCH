package com.cch.cyclingmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PhaseDto {
    private Long id;
    @NotBlank
    @Size(max = 100)
    private String name;

    @Positive
    private double distance;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    private Long competitionId;
}