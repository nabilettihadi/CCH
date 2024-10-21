package com.cch.cyclingmanager.dto;

import lombok.Data;

@Data
public class GeneralResultDto {
    private Long competitionId;
    private Long cyclistId;
    private int rank;
    private double totalTime;
}