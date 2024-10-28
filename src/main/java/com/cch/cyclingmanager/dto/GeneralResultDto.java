package com.cch.cyclingmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Duration;

@Data
public class GeneralResultDto {
    @NotNull
    private Long competitionId;

    @NotNull
    private Long cyclistId;

    @Positive
    private int rank;

    @NotNull
    private Duration totalTime;

    public GeneralResultDto() {
    }

    public GeneralResultDto(Long competitionId, Long cyclistId, int rank, Duration totalTime) {
        this.competitionId = competitionId;
        this.cyclistId = cyclistId;
        this.rank = rank;
        this.totalTime = totalTime;
    }
}