package com.cch.cyclingmanager.controller;

import com.cch.cyclingmanager.dto.CyclistDto;
import com.cch.cyclingmanager.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cyclists")
public class CyclistController {

    @Autowired
    private CyclistService cyclistService;

    @GetMapping
    public ResponseEntity<List<CyclistDto>> getAllCyclists() {
        List<CyclistDto> cyclists = cyclistService.findAll();
        return new ResponseEntity<>(cyclists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistDto> getCyclistById(@PathVariable Long id) {
        return cyclistService.findById(id)
                .map(cyclist -> new ResponseEntity<>(cyclist, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CyclistDto> createCyclist(@RequestBody CyclistDto cyclistDto) {
        CyclistDto createdCyclist = cyclistService.save(cyclistDto);
        return new ResponseEntity<>(createdCyclist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistDto> updateCyclist(@PathVariable Long id, @RequestBody CyclistDto cyclistDto) {
        if (!id.equals(cyclistDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CyclistDto updatedCyclist = cyclistService.update(cyclistDto);
        return new ResponseEntity<>(updatedCyclist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyclist(@PathVariable Long id) {
        cyclistService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}