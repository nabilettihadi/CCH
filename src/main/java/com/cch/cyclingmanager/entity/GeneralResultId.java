package com.cch.cyclingmanager.entity;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;
}