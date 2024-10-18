package com.cch.cyclingmanager.entity;

import com.cch.cyclingmanager.entity.embeddable.ResultId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;
import java.time.Duration;

@Data
@Entity
public class Result {
    @EmbeddedId
    @NotNull
    private ResultId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    @NotNull
    private Cyclist cyclist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phaseId")
    @JoinColumn(name = "phase_id")
    @NotNull
    private Phase phase;

    @NotNull
    private Duration time;

    @Min(1)
    private int position;
}