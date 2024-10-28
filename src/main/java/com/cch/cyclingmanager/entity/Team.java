package com.cch.cyclingmanager.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String country;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cyclist> cyclists;
}