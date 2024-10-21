package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.Duration;

@Data
public class ResultDto {
    private Long phaseId;
    private Long cyclistId;
    private int rank;
    private Duration time;

    public ResultDto() {}

    public ResultDto(Long phaseId, Long cyclistId, int rank, Duration time) {
        this.phaseId = phaseId;
        this.cyclistId = cyclistId;
        this.rank = rank;
        this.time = time;
    }
}