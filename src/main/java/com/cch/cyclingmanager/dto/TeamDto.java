package com.cch.cyclingmanager.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private String country;
    private Set<CyclistDto> cyclists;
}