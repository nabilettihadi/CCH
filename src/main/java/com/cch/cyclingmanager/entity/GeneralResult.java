package com.cch.cyclingmanager.entity;

import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;

import java.time.Duration;

@Data
@Entity
public class GeneralResult {
    @EmbeddedId
    @NotNull
    private GeneralResultId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    @NotNull
    private Cyclist cyclist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    @NotNull
    private Competition competition;

    @NotNull
    private Duration totalTime;

    @Min(1)
    private int finalPosition;
}