package com.cch.cyclingmanager.controller;

import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public ResponseEntity<List<CompetitionDto>> getAllCompetitions() {
        List<CompetitionDto> competitions = competitionService.findAll();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable Long id) {
        return competitionService.findById(id)
                .map(competition -> new ResponseEntity<>(competition, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody CompetitionDto competitionDto) {
        CompetitionDto createdCompetition = competitionService.save(competitionDto);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDto> updateCompetition(@PathVariable Long id, @RequestBody CompetitionDto competitionDto) {
        if (!id.equals(competitionDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CompetitionDto updatedCompetition = competitionService.update(competitionDto);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<CompetitionDto>> getCompetitionsByLocation(@PathVariable String location) {
        List<CompetitionDto> competitions = competitionService.findByLocation(location);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<CompetitionDto>> getCompetitionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<CompetitionDto> competitions = competitionService.findByDateRange(startDate, endDate);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }
}