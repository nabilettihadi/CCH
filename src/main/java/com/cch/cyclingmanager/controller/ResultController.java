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

    @GetMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<ResultDto> getResultById(
            @PathVariable Long stageId,
            @PathVariable Long cyclistId) {
        return resultService.findById(new ResultId(stageId, cyclistId))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResultDto> createResult(@RequestBody ResultDto resultDto) {
        ResultDto createdResult = resultService.save(resultDto);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @DeleteMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<Void> deleteResult(
            @PathVariable Long stageId,
            @PathVariable Long cyclistId) {
        resultService.deleteById(new ResultId(stageId, cyclistId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}