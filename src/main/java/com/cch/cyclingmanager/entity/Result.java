package com.cch.cyclingmanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;

@Data
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    private Duration time;
    private int position;
}