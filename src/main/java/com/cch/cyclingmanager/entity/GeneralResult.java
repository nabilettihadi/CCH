package com.cch.cyclingmanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;

@Data
@Entity
public class GeneralResult {
    @EmbeddedId
    private GeneralResultId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private Duration totalTime;
    private int finalPosition;
}