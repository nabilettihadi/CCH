package com.cch.cyclingmanager.entity.embeddable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class GeneralResultId implements Serializable {
    @NotNull
    private Long competitionId;
    
    @NotNull
    private Long cyclistId;

    
    public GeneralResultId() {}

    
    public GeneralResultId(Long competitionId, Long cyclistId) {
        this.competitionId = competitionId;
        this.cyclistId = cyclistId;
    }
}