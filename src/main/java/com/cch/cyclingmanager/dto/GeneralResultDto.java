package com.cch.cyclingmanager.dto;

import jakarta.validation.constraints.Min;
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

    @Min(0)
    private int finalPosition;

    @NotNull
    private Duration totalTime;

    public GeneralResultDto() {
    }

    public GeneralResultDto(Long competitionId, Long cyclistId, int finalPosition, Duration totalTime) {
        this.competitionId = competitionId;
        this.cyclistId = cyclistId;
        this.finalPosition = finalPosition;
        this.totalTime = totalTime;
    }
}