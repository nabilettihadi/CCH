package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.Duration;

@Data
public class GeneralResultDto {
    private Long competitionId;
    private Long cyclistId;
    private int rank;
    private Duration totalTime;

    public GeneralResultDto() {}

    public GeneralResultDto(Long competitionId, Long cyclistId, int rank, Duration totalTime) {
        this.competitionId = competitionId;
        this.cyclistId = cyclistId;
        this.rank = rank;
        this.totalTime = totalTime;
    }
}