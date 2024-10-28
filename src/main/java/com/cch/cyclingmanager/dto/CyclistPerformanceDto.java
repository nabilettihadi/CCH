package com.cch.cyclingmanager.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Duration;

@Data
public class CyclistPerformanceDto {
    @NotNull
    @Valid
    private CompetitionDto competition;

    @Positive
    private int rank;

    @NotNull
    private Duration totalTime;

    public CyclistPerformanceDto(CompetitionDto competition, int rank, Duration totalTime) {
        this.competition = competition;
        this.rank = rank;
        this.totalTime = totalTime;
    }
}