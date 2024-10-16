package com.cch.cyclingmanager.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.Duration;

@Data
@Entity
public class Result {
    @EmbeddedId
    private ResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("phaseId")
    @JoinColumn(name = "phase_id")
    private Phase phase;

    private Duration time;
    private int position;
}