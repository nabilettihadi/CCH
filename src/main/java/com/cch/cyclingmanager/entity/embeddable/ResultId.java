package com.cch.cyclingmanager.entity.embeddable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Data
@Embeddable
public class ResultId implements Serializable {
    @NotNull
    private Long cyclistId;

    @NotNull
    private Long phaseId;

    public ResultId() {
    }

    public ResultId(Long phaseId, Long cyclistId) {
        this.phaseId = phaseId;
        this.cyclistId = cyclistId;
    }
}