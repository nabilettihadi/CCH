package com.cch.cyclingmanager.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.Set;

@Data
public class TeamDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String country;

    private Set<CyclistDto> cyclists;
}