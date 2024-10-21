package com.cch.cyclingmanager.entity.embeddable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class GeneralResultId implements Serializable {
    private Long competitionId;
    private Long cyclistId;

    
    public GeneralResultId() {}

    
    public GeneralResultId(Long competitionId, Long cyclistId) {
        this.competitionId = competitionId;
        this.cyclistId = cyclistId;
    }
}