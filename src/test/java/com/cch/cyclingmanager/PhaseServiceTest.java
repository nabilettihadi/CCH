package com.cch.cyclingmanager;

import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PhaseServiceTest {

    @Test
    void testBasicPhaseCreation() {
        Phase phase = new Phase();
        phase.setId(1L);
        phase.setName("Mountain Stage");
        phase.setDistance(150.5);
        phase.setDate(LocalDate.of(2024, 7, 1));

        assertEquals(1L, phase.getId());
        assertEquals("Mountain Stage", phase.getName());
        assertEquals(150.5, phase.getDistance());
        assertEquals(LocalDate.of(2024, 7, 1), phase.getDate());
    }

    @Test
    void testPhaseWithCompetitionAndResults() {
        Phase phase = new Phase();
        phase.setId(1L);

        Competition competition = new Competition();
        competition.setId(1L);
        competition.setName("Tour de France");

        Set<Result> results = new HashSet<>();
        Result result = new Result();
        ResultId resultId = new ResultId(1L, 1L);
        result.setId(resultId);

        Cyclist cyclist = new Cyclist();
        cyclist.setId(1L);
        result.setCyclist(cyclist);
        result.setPhase(phase);
        result.setTime(Duration.ofHours(2));
        result.setPosition(1);

        results.add(result);

        phase.setCompetition(competition);
        phase.setResults(results);

        assertNotNull(phase.getCompetition());
        assertNotNull(phase.getResults());
        assertEquals(1, phase.getResults().size());
        assertEquals("Tour de France", phase.getCompetition().getName());
    }

    @Test
    void testPhaseEquality() {
        Phase phase1 = new Phase();
        phase1.setId(1L);
        phase1.setName("Time Trial");
        phase1.setDistance(42.5);

        Phase phase2 = new Phase();
        phase2.setId(1L);
        phase2.setName("Time Trial");
        phase2.setDistance(42.5);

        assertEquals(phase1, phase2);
        assertEquals(phase1.hashCode(), phase2.hashCode());
    }

    @Test
    void testInvalidDistance() {
        Phase phase = new Phase();
        assertThrows(IllegalArgumentException.class, () -> {
            phase.setDistance(-10.0);
        });
    }

    @Test
    void testPhaseWithNullCompetition() {
        Phase phase = new Phase();
        assertThrows(NullPointerException.class, () -> {
            phase.setCompetition(null);
        });
    }

    @Test
    void testPhaseWithNullDate() {
        Phase phase = new Phase();
        assertThrows(NullPointerException.class, () -> {
            phase.setDate(null);
        });
    }

    @Test
    void testPhaseWithEmptyName() {
        Phase phase = new Phase();
        assertThrows(IllegalArgumentException.class, () -> {
            phase.setName("");
        });
    }
}