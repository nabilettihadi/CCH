package com.cch.cyclingmanager.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CompetitionDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;

    @NotBlank
    @Size(max = 100)
    private String location;

    private Set<PhaseDto> phases;

    @AssertTrue(message = "La date de fin doit être postérieure à la date de début")
    private boolean isEndDateAfterStartDate() {
        return endDate == null || startDate == null || endDate.isAfter(startDate);
    }
}