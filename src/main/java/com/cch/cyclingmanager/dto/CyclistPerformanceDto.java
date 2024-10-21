package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.Duration;

@Data
public class CyclistPerformanceDto {
    private CompetitionDto competition;
    private int rank;
    private Duration totalTime;

    public CyclistPerformanceDto(CompetitionDto competition, int rank, Duration totalTime) {
        this.competition = competition;
        this.rank = rank;
        this.totalTime = totalTime;
    }
}