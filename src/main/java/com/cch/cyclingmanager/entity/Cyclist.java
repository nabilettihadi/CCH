package com.cch.cyclingmanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Result> results;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults;
}