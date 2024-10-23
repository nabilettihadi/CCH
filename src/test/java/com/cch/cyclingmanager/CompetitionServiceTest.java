package com.cch.cyclingmanager;

import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import com.cch.cyclingmanager.service.impl.CompetitionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    private CompetitionDto competitionDto;
    private Competition competition;

    @BeforeEach
    void setUp() {
        competitionDto = new CompetitionDto();
        competitionDto.setId(1L);
        competitionDto.setName("Tour de France");
        competitionDto.setStartDate(LocalDate.of(2023, 7, 1));
        competitionDto.setEndDate(LocalDate.of(2023, 7, 23));
        competitionDto.setLocation("France");

        competition = new Competition();
        competition.setId(1L);
        competition.setName("Tour de France");
        competition.setStartDate(LocalDate.of(2023, 7, 1));
        competition.setEndDate(LocalDate.of(2023, 7, 23));
        competition.setLocation("France");
    }

    @Test
    void testSave() {
        when(modelMapper.map(competitionDto, Competition.class)).thenReturn(competition);
        when(competitionRepository.save(competition)).thenReturn(competition);
        when(modelMapper.map(competition, CompetitionDto.class)).thenReturn(competitionDto);

        CompetitionDto savedCompetitionDto = competitionService.save(competitionDto);

        assertNotNull(savedCompetitionDto);
        assertEquals(competitionDto.getId(), savedCompetitionDto.getId());
        assertEquals(competitionDto.getName(), savedCompetitionDto.getName());
        assertEquals(competitionDto.getStartDate(), savedCompetitionDto.getStartDate());
        assertEquals(competitionDto.getEndDate(), savedCompetitionDto.getEndDate());
        assertEquals(competitionDto.getLocation(), savedCompetitionDto.getLocation());

        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void testUpdate() {
        when(modelMapper.map(competitionDto, Competition.class)).thenReturn(competition);
        when(competitionRepository.save(competition)).thenReturn(competition);
        when(modelMapper.map(competition, CompetitionDto.class)).thenReturn(competitionDto);

        CompetitionDto updatedCompetitionDto = competitionService.update(competitionDto);

        assertNotNull(updatedCompetitionDto);
        assertEquals(competitionDto.getId(), updatedCompetitionDto.getId());
        assertEquals(competitionDto.getName(), updatedCompetitionDto.getName());
        assertEquals(competitionDto.getStartDate(), updatedCompetitionDto.getStartDate());
        assertEquals(competitionDto.getEndDate(), updatedCompetitionDto.getEndDate());
        assertEquals(competitionDto.getLocation(), updatedCompetitionDto.getLocation());

        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void testUpdateWithNullId() {
        competitionDto.setId(null);
        assertThrows(IllegalArgumentException.class, () -> competitionService.update(competitionDto));
    }

    @Test
    void testFindById() {
        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));
        when(modelMapper.map(competition, CompetitionDto.class)).thenReturn(competitionDto);

        Optional<CompetitionDto> foundCompetitionDto = competitionService.findById(1L);

        assertTrue(foundCompetitionDto.isPresent());
        assertEquals(competitionDto.getId(), foundCompetitionDto.get().getId());
        assertEquals(competitionDto.getName(), foundCompetitionDto.get().getName());
        assertEquals(competitionDto.getStartDate(), foundCompetitionDto.get().getStartDate());
        assertEquals(competitionDto.getEndDate(), foundCompetitionDto.get().getEndDate());
        assertEquals(competitionDto.getLocation(), foundCompetitionDto.get().getLocation());

        verify(competitionRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(competitionRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CompetitionDto> foundCompetitionDto = competitionService.findById(1L);

        assertFalse(foundCompetitionDto.isPresent());
        verify(competitionRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        List<Competition> competitions = Arrays.asList(competition, competition);
        when(competitionRepository.findAll()).thenReturn(competitions);
        when(modelMapper.map(competition, CompetitionDto.class)).thenReturn(competitionDto);

        List<CompetitionDto> foundCompetitions = competitionService.findAll();

        assertNotNull(foundCompetitions);
        assertEquals(2, foundCompetitions.size());
        verify(competitionRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        when(modelMapper.map(competitionDto, Competition.class)).thenReturn(competition);
        doNothing().when(competitionRepository).delete(competition);

        competitionService.delete(competitionDto);

        verify(competitionRepository, times(1)).delete(competition);
    }

    @Test
    void testDeleteById() {
        doNothing().when(competitionRepository).deleteById(1L);

        competitionService.deleteById(1L);

        verify(competitionRepository, times(1)).deleteById(1L);
    }
}