package com.cch.cyclingmanager.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CyclistDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private Long teamId;
}