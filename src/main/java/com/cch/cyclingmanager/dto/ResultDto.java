package com.cch.cyclingmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Duration;

@Data
public class ResultDto {
    @NotNull
    private Long phaseId;

    @NotNull
    private Long cyclistId;

    @Positive
    private int rank;

    @NotNull
    private Duration time;

    public ResultDto() {
    }

    public ResultDto(Long phaseId, Long cyclistId, int rank, Duration time) {
        this.phaseId = phaseId;
        this.cyclistId = cyclistId;
        this.rank = rank;
        this.time = time;
    }
}