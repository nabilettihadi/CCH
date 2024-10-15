package com.cch.cyclingmanager.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
@Entity
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double distance;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "phase")
    private Set<Result> results;
}