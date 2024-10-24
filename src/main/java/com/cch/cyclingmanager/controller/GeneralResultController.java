package com.cch.cyclingmanager.controller;

import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import com.cch.cyclingmanager.service.GeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/general-results")
public class GeneralResultController {

    @Autowired
    private GeneralResultService generalResultService;

    @GetMapping
    public ResponseEntity<List<GeneralResultDto>> getAllGeneralResults() {
        List<GeneralResultDto> generalResults = generalResultService.findAll();
        return new ResponseEntity<>(generalResults, HttpStatus.OK);
    }

    @GetMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultDto> getGeneralResultById(
            @PathVariable Long competitionId,
            @PathVariable Long cyclistId) {
        return generalResultService.findById(new GeneralResultId(competitionId, cyclistId))
                .map(generalResult -> new ResponseEntity<>(generalResult, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<GeneralResultDto> createGeneralResult(@RequestBody GeneralResultDto generalResultDto) {
        GeneralResultDto createdGeneralResult = generalResultService.save(generalResultDto);
        return new ResponseEntity<>(createdGeneralResult, HttpStatus.CREATED);
    }

    @PutMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultDto> updateGeneralResult(
            @PathVariable Long competitionId,
            @PathVariable Long cyclistId,
            @RequestBody GeneralResultDto generalResultDto) {
        if (!competitionId.equals(generalResultDto.getCompetitionId()) ||
                !cyclistId.equals(generalResultDto.getCyclistId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GeneralResultDto updatedGeneralResult = generalResultService.save(generalResultDto);
        return new ResponseEntity<>(updatedGeneralResult, HttpStatus.OK);
    }

    @DeleteMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<Void> deleteGeneralResult(
            @PathVariable Long competitionId,
            @PathVariable Long cyclistId) {
        generalResultService.deleteById(new GeneralResultId(competitionId, cyclistId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}