package com.cch.cyclingmanager.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100)
    private String name;

    @Positive(message = "La distance doit être positive")
    private double distance;

    @NotNull(message = "La date ne peut pas être nulle")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    @NotNull(message = "La compétition ne peut pas être nulle")
    private Competition competition;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Result> results;

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        this.name = name;
    }

    public void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("La distance doit être positive");
        }
        this.distance = distance;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("La date ne peut pas être nulle");
        }
        this.date = date;
    }

    public void setCompetition(Competition competition) {
        if (competition == null) {
            throw new NullPointerException("La compétition ne peut pas être nulle");
        }
        this.competition = competition;
    }
}