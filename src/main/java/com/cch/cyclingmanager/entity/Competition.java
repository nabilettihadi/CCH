package com.cch.cyclingmanager.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Future
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;

    @NotBlank
    @Size(max = 100)
    private String location;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phase> phases;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults;
}