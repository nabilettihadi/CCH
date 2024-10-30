package com.cch.cyclingmanager;

import com.cch.cyclingmanager.dto.GeneralResultDto;
import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.dto.CyclistDto;
import com.cch.cyclingmanager.dto.CompetitionReportDto;
import com.cch.cyclingmanager.dto.CyclistPerformanceDto;
import com.cch.cyclingmanager.entity.GeneralResult;
import com.cch.cyclingmanager.entity.embeddable.GeneralResultId;
import com.cch.cyclingmanager.repository.GeneralResultRepository;
import com.cch.cyclingmanager.service.CompetitionService;
import com.cch.cyclingmanager.service.CyclistService;
import com.cch.cyclingmanager.service.impl.GeneralResultServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GeneralResultServiceTest {


    @Mock
    private GeneralResultRepository generalResultRepository;

    @Mock
    private CompetitionService competitionService;
    @Mock
    private CyclistService cyclistService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GeneralResultServiceImpl generalResultService;

    private GeneralResultDto generalResultDto;
    private GeneralResult generalResult;
    private GeneralResultId generalResultId;

    @BeforeEach
    void setUp() {
        generalResultId = new GeneralResultId(1L, 1L);

        generalResultDto = new GeneralResultDto();
        generalResultDto.setCompetitionId(1L);
        generalResultDto.setCyclistId(1L);
        generalResultDto.setRank(1);
        generalResultDto.setTotalTime(Duration.ofSeconds(3600));

        generalResult = new GeneralResult();
        generalResult.setId(generalResultId);
        generalResult.setFinalPosition(1);
        generalResult.setTotalTime(Duration.ofSeconds(3600));
    }

    @Test
    void testSave() {
        when(modelMapper.map(generalResultDto, GeneralResult.class)).thenReturn(generalResult);
        when(generalResultRepository.save(generalResult)).thenReturn(generalResult);
        when(modelMapper.map(generalResult, GeneralResultDto.class)).thenReturn(generalResultDto);

        GeneralResultDto savedGeneralResultDto = generalResultService.save(generalResultDto);

        assertNotNull(savedGeneralResultDto);
        assertEquals(generalResultDto.getCompetitionId(), savedGeneralResultDto.getCompetitionId());
        assertEquals(generalResultDto.getCyclistId(), savedGeneralResultDto.getCyclistId());
        assertEquals(generalResultDto.getRank(), savedGeneralResultDto.getRank());
        assertEquals(generalResultDto.getTotalTime(), savedGeneralResultDto.getTotalTime());

        verify(generalResultRepository, times(1)).save(generalResult);
    }

    @Test
    void testFindById() {
        when(generalResultRepository.findById(generalResultId)).thenReturn(Optional.of(generalResult));
        when(modelMapper.map(generalResult, GeneralResultDto.class)).thenReturn(generalResultDto);

        Optional<GeneralResultDto> foundGeneralResultDto = generalResultService.findById(generalResultId);

        assertTrue(foundGeneralResultDto.isPresent());
        assertEquals(generalResultDto.getCompetitionId(), foundGeneralResultDto.get().getCompetitionId());
        assertEquals(generalResultDto.getCyclistId(), foundGeneralResultDto.get().getCyclistId());
        assertEquals(generalResultDto.getRank(), foundGeneralResultDto.get().getRank());
        assertEquals(generalResultDto.getTotalTime(), foundGeneralResultDto.get().getTotalTime());

        verify(generalResultRepository, times(1)).findById(generalResultId);
    }

    @Test
    void testFindAll() {
        List<GeneralResult> generalResults = Arrays.asList(generalResult, generalResult);
        when(generalResultRepository.findAll()).thenReturn(generalResults);
        when(modelMapper.map(generalResult, GeneralResultDto.class)).thenReturn(generalResultDto);

        List<GeneralResultDto> foundGeneralResults = generalResultService.findAll();

        assertNotNull(foundGeneralResults);
        assertEquals(2, foundGeneralResults.size());
        verify(generalResultRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        when(modelMapper.map(generalResultDto, GeneralResult.class)).thenReturn(generalResult);
        doNothing().when(generalResultRepository).delete(generalResult);

        generalResultService.delete(generalResultDto);

        verify(generalResultRepository, times(1)).delete(generalResult);
    }

    @Test
    void testDeleteById() {
        doNothing().when(generalResultRepository).deleteById(generalResultId);

        generalResultService.deleteById(generalResultId);

        verify(generalResultRepository, times(1)).deleteById(generalResultId);
    }

    @Test
    void testRegister() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(1L);
        when(competitionService.findById(1L)).thenReturn(Optional.of(competitionDto));

        GeneralResultDto newGeneralResultDto = new GeneralResultDto(1L, 1L, 0, Duration.ZERO);
        when(modelMapper.map(any(GeneralResultDto.class), eq(GeneralResult.class))).thenReturn(generalResult);
        when(generalResultRepository.save(any(GeneralResult.class))).thenReturn(generalResult);
        when(modelMapper.map(generalResult, GeneralResultDto.class)).thenReturn(newGeneralResultDto);

        GeneralResultDto registeredGeneralResultDto = generalResultService.register(1L, 1L);

        assertNotNull(registeredGeneralResultDto);
        assertEquals(1L, registeredGeneralResultDto.getCompetitionId());
        assertEquals(1L, registeredGeneralResultDto.getCyclistId());
        assertEquals(0, registeredGeneralResultDto.getRank());
        assertEquals(Duration.ZERO, registeredGeneralResultDto.getTotalTime());

        verify(generalResultRepository, times(1)).save(any(GeneralResult.class));
    }

    @Test
    void testUnregister() {
        doNothing().when(generalResultRepository).deleteById(any(GeneralResultId.class));

        generalResultService.unregister(1L, 1L);

        verify(generalResultRepository, times(1)).deleteById(any(GeneralResultId.class));
    }

    @Test
    void testGetCompetitionRankings() {
        List<GeneralResult> generalResults = Arrays.asList(
                createGeneralResult(1L, 1L, Duration.ofSeconds(3600)),
                createGeneralResult(1L, 2L, Duration.ofSeconds(3500))
        );
        when(generalResultRepository.findByIdCompetitionId(1L)).thenReturn(generalResults);
        when(modelMapper.map(any(GeneralResult.class), eq(GeneralResultDto.class))).thenAnswer(invocation -> {
            GeneralResult gr = invocation.getArgument(0);
            return new GeneralResultDto(gr.getId().getCompetitionId(), gr.getId().getCyclistId(), 0, gr.getTotalTime());
        });

        List<GeneralResultDto> rankings = generalResultService.getCompetitionRankings(1L);

        assertNotNull(rankings);
        assertEquals(2, rankings.size());
        assertEquals(1, rankings.get(0).getRank());
        assertEquals(2, rankings.get(1).getRank());
        assertEquals(Duration.ofSeconds(3500), rankings.get(0).getTotalTime());
        assertEquals(Duration.ofSeconds(3600), rankings.get(1).getTotalTime());
    }

    @Test
    void testGenerateCompetitionReport() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(1L);
        competitionDto.setName("Test Competition");

        CyclistDto cyclistDto = new CyclistDto();
        cyclistDto.setId(1L);
        cyclistDto.setFirstName("Test");
        cyclistDto.setLastName("Cyclist");

        List<GeneralResult> generalResults = Arrays.asList(
                createGeneralResult(1L, 1L, Duration.ofSeconds(3600))
        );

        when(competitionService.findById(1L)).thenReturn(Optional.of(competitionDto));
        when(generalResultRepository.findByIdCompetitionId(1L)).thenReturn(generalResults);
        when(modelMapper.map(any(GeneralResult.class), eq(GeneralResultDto.class))).thenAnswer(invocation -> {
            GeneralResult gr = invocation.getArgument(0);
            return new GeneralResultDto(gr.getId().getCompetitionId(), gr.getId().getCyclistId(), 1, gr.getTotalTime());
        });
        when(cyclistService.findById(1L)).thenReturn(Optional.of(cyclistDto));

        CompetitionReportDto report = generalResultService.generateCompetitionReport(1L);

        assertNotNull(report);
        assertEquals(competitionDto, report.getCompetition());
        assertEquals(1, report.getParticipants().size());
        assertEquals(1, report.getRankings().size());
    }

    @Test
    void testGetCyclistPerformanceHistory() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(1L);
        competitionDto.setName("Test Competition");

        List<GeneralResult> generalResults = Arrays.asList(
                createGeneralResult(1L, 1L, Duration.ofSeconds(3600))
        );

        when(generalResultRepository.findByIdCyclistId(1L)).thenReturn(generalResults);
        when(modelMapper.map(any(GeneralResult.class), eq(GeneralResultDto.class))).thenAnswer(invocation -> {
            GeneralResult gr = invocation.getArgument(0);
            return new GeneralResultDto(gr.getId().getCompetitionId(), gr.getId().getCyclistId(), 1, gr.getTotalTime());
        });
        when(competitionService.findById(1L)).thenReturn(Optional.of(competitionDto));

        List<CyclistPerformanceDto> performanceHistory = generalResultService.getCyclistPerformanceHistory(1L);

        assertNotNull(performanceHistory);
        assertEquals(1, performanceHistory.size());
        assertEquals(competitionDto, performanceHistory.get(0).getCompetition());
        assertEquals(1, performanceHistory.get(0).getRank());
        assertEquals(Duration.ofSeconds(3600), performanceHistory.get(0).getTotalTime());
    }

    @Test
    void testUpdateGeneralResultForNonRegisteredCyclist() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(2L);
        when(competitionService.findById(2L)).thenReturn(Optional.of(competitionDto));

        when(generalResultRepository.findById(any(GeneralResultId.class))).thenReturn(Optional.empty());
        when(modelMapper.map(any(GeneralResultDto.class), eq(GeneralResult.class))).thenReturn(new GeneralResult());

        generalResultService.updateGeneralResult(2L, 1L, Duration.ofSeconds(3600));

        verify(generalResultRepository).save(any(GeneralResult.class));
    }

    @Test
    void testGenerateCompetitionReportWithNoParticipants() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(1L);
        competitionDto.setName("Test Competition");

        when(competitionService.findById(1L)).thenReturn(Optional.of(competitionDto));
        when(generalResultRepository.findByIdCompetitionId(1L)).thenReturn(Arrays.asList());

        CompetitionReportDto report = generalResultService.generateCompetitionReport(1L);

        assertNotNull(report);
        assertEquals(competitionDto, report.getCompetition());
        assertTrue(report.getParticipants().isEmpty());
        assertTrue(report.getRankings().isEmpty());
    }

    private GeneralResult createGeneralResult(Long competitionId, Long cyclistId, Duration totalTime) {
        GeneralResult gr = new GeneralResult();
        gr.setId(new GeneralResultId(competitionId, cyclistId));
        gr.setTotalTime(totalTime);
        return gr;
    }
}