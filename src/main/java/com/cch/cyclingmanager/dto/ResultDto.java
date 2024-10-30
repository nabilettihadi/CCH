package com.cch.cyclingmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;

@Data
public class ResultDto {
    @NotNull
    private Long phaseId;

    @NotNull
    private Long cyclistId;

    @Min(0)
    private int position;

    @NotNull
    private Duration time;

    public ResultDto() {
    }

    public ResultDto(Long phaseId, Long cyclistId, int rank, Duration time) {
        this.phaseId = phaseId;
        this.cyclistId = cyclistId;
        this.position = rank;
        this.time = time;
    }
}