package com.cch.cyclingmanager.entity;

import jakarta.persistence.CascadeType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cyclist> cyclists;
}