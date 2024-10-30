package com.cch.cyclingmanager;

import com.cch.cyclingmanager.dto.ResultDto;
import com.cch.cyclingmanager.dto.PhaseDto;
import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.Phase;
import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.entity.embeddable.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import com.cch.cyclingmanager.service.PhaseService;
import com.cch.cyclingmanager.service.GeneralResultService;
import com.cch.cyclingmanager.service.impl.ResultServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private PhaseService phaseService;

    @Mock
    private GeneralResultService generalResultService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ResultServiceImpl resultService;

    private ResultDto resultDto;
    private Result result;
    private ResultId resultId;
    private PhaseDto phaseDto;

    @BeforeEach
    void setUp() {
        resultId = new ResultId(1L, 1L);

        resultDto = new ResultDto();
        resultDto.setPhaseId(1L);
        resultDto.setCyclistId(1L);
        resultDto.setTime(Duration.ofMinutes(30));
        resultDto.setPosition(1);

        result = new Result();
        result.setId(resultId);
        result.setTime(Duration.ofMinutes(30));
        result.setPosition(1);

        phaseDto = new PhaseDto();
        phaseDto.setId(1L);
        phaseDto.setCompetitionId(1L);
    }

    @Test
    void testCreateWithNullPhaseId() {
        ResultDto invalidDto = new ResultDto();
        assertThrows(IllegalArgumentException.class, () -> resultService.create(invalidDto));
    }

    @Test
    void testCreateWithNonExistentPhase() {
        when(phaseService.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> resultService.create(resultDto));
    }

    @Test
    void testUpdateWithNullIds() {
        ResultDto invalidDto = new ResultDto();
        assertThrows(IllegalArgumentException.class, () -> resultService.update(invalidDto));
    }

    @Test
    void testUpdateWithNonExistentResult() {
        when(resultRepository.findById(any(ResultId.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> resultService.update(resultDto));
    }

    @Test
    void testCalculateRankingsWithEmptyResults() {
        when(resultRepository.findByPhaseId(anyLong())).thenReturn(Collections.emptyList());
        when(phaseService.findById(anyLong())).thenReturn(Optional.of(phaseDto));

        resultService.calculateRankings(1L);

        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void testCalculateRankingsWithNonExistentPhase() {
        when(phaseService.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> resultService.calculateRankings(1L));
    }

    @Test
    void testGetResultsForCompetitionWithNoPhases() {
        when(phaseService.findByCompetitionId(anyLong())).thenReturn(Collections.emptyList());

        List<ResultDto> results = resultService.getResultsForCompetition(1L);

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindByIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> resultService.findById(null));
    }

    @Test
    void testDeleteByIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> resultService.deleteById(null));
    }

    @Test
    void testFindByPhaseIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> resultService.findByPhaseId(null));
    }

    @Test
    void testFindByCyclistIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> resultService.findByCyclistId(null));
    }

    @Test
    void testUpdateWithNegativeTime() {
        resultDto.setTime(Duration.ofSeconds(-1));
        assertThrows(IllegalArgumentException.class, () -> resultService.update(resultDto));
    }

    @Test
    void testUpdateWithNegativeRank() {
        resultDto.setPosition(-1);
        assertThrows(IllegalArgumentException.class, () -> resultService.update(resultDto));
    }

    private Result createResult(Long phaseId, Long cyclistId, Duration time) {
        Result result = new Result();
        result.setId(new ResultId(phaseId, cyclistId));
        result.setTime(time);
        return result;
    }

}