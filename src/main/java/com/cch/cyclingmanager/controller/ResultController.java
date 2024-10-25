package com.cch.cyclingmanager.controller;

import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public ResponseEntity<List<ResultDto>> getAllResults() {
        List<ResultDto> results = resultService.findAll();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{phaseId}/{cyclistId}")
    public ResponseEntity<ResultDto> getResultById(
            @PathVariable Long phaseId,
            @PathVariable Long cyclistId) {
        return resultService.findById(new ResultId(phaseId, cyclistId))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResultDto> createResult(@RequestBody ResultDto resultDto) {
        if (resultDto.getPhaseId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResultDto createdResult = resultService.create(resultDto);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }
    
    @PutMapping("/{phaseId}/{cyclistId}")
    public ResponseEntity<ResultDto> updateResult(
            @PathVariable Long phaseId,
            @PathVariable Long cyclistId,
            @RequestBody ResultDto resultDto) {
        if (!phaseId.equals(resultDto.getPhaseId()) || !cyclistId.equals(resultDto.getCyclistId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResultDto updatedResult = resultService.update(resultDto);
        return new ResponseEntity<>(updatedResult, HttpStatus.OK);
    }

    @DeleteMapping("/{phaseId}/{cyclistId}")
    public ResponseEntity<Void> deleteResult(
            @PathVariable Long phaseId,
            @PathVariable Long cyclistId) {
        resultService.deleteById(new ResultId(phaseId, cyclistId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}