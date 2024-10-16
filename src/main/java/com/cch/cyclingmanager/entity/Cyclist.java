package com.cch.cyclingmanager.entity;

import lombok.Data;

import jakarta.persistence.*;
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
    private String team;

    @OneToMany(mappedBy = "cyclist")
    private Set<Result> results;
}
