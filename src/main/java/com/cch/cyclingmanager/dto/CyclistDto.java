package com.cch.cyclingmanager.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class CyclistDto {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Past
    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 50)
    private String nationality;

    private Long teamId;
}