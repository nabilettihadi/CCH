package com.cch.cyclingmanager.entity;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ResultId implements Serializable {
    private Long cyclistId;
    private Long phaseId;
}