package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompetitionReportDto {
    private CompetitionDto competition;
    private List<CyclistDto> participants;
    private List<GeneralResultDto> rankings;

    public CompetitionReportDto(CompetitionDto competition, List<CyclistDto> participants, List<GeneralResultDto> rankings) {
        this.competition = competition;
        this.participants = participants;
        this.rankings = rankings;
    }
}