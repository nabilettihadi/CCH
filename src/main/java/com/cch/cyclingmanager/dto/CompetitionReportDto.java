package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
@Data
public class CompetitionReportDto {
    @NotNull
    @Valid
    private CompetitionDto competition;

    @NotNull
    @Size(min = 1)
    private List<@Valid CyclistDto> participants;

    @NotNull
    @Size(min = 1)
    private List<@Valid GeneralResultDto> rankings;


    public CompetitionReportDto(CompetitionDto competition, List<CyclistDto> participants, List<GeneralResultDto> rankings) {
        this.competition = competition;
        this.participants = participants;
        this.rankings = rankings;
    }
}