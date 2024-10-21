package com.cch.cyclingmanager.dto;

import lombok.Data;

@Data
public class ResultDto {
    private Long phaseId;
    private Long cyclistId;
    private int rank;
    private double time;
}