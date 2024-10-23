package com.cch.cyclingmanager.controller;

import com.cch.cyclingmanager.dto.PhaseDto;
import com.cch.cyclingmanager.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stages")
public class PhaseController {

    @Autowired
    private PhaseService phaseService;

    @GetMapping
    public ResponseEntity<List<PhaseDto>> getAllPhases() {
        List<PhaseDto> phases = phaseService.findAll();
        return new ResponseEntity<>(phases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhaseDto> getPhaseById(@PathVariable Long id) {
        return phaseService.findById(id)
                .map(phase -> new ResponseEntity<>(phase, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PhaseDto> createPhase(@RequestBody PhaseDto phaseDto) {
        PhaseDto createdPhase = phaseService.save(phaseDto);
        return new ResponseEntity<>(createdPhase, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhaseDto> updatePhase(@PathVariable Long id, @RequestBody PhaseDto phaseDto) {
        if (!id.equals(phaseDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PhaseDto updatedPhase = phaseService.save(phaseDto);
        return new ResponseEntity<>(updatedPhase, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhase(@PathVariable Long id) {
        phaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}