package com.cch.cyclingmanager;

import com.cch.cyclingmanager.dto.TeamDto;
import com.cch.cyclingmanager.entity.Team;
import com.cch.cyclingmanager.repository.TeamRepository;
import com.cch.cyclingmanager.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    private TeamDto teamDto;
    private Team team;

    @BeforeEach
    void setUp() {
        teamDto = new TeamDto();
        teamDto.setId(1L);
        teamDto.setName("Test Team");
        teamDto.setCountry("Test Country");

        team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        team.setCountry("Test Country");
    }

    @Test
    void testSave() {
        when(modelMapper.map(teamDto, Team.class)).thenReturn(team);
        when(teamRepository.save(team)).thenReturn(team);
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        TeamDto savedTeamDto = teamService.save(teamDto);

        assertNotNull(savedTeamDto);
        assertEquals(teamDto.getId(), savedTeamDto.getId());
        assertEquals(teamDto.getName(), savedTeamDto.getName());
        assertEquals(teamDto.getCountry(), savedTeamDto.getCountry());

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void testUpdate() {
        when(modelMapper.map(teamDto, Team.class)).thenReturn(team);
        when(teamRepository.save(team)).thenReturn(team);
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        TeamDto updatedTeamDto = teamService.update(teamDto);

        assertNotNull(updatedTeamDto);
        assertEquals(teamDto.getId(), updatedTeamDto.getId());
        assertEquals(teamDto.getName(), updatedTeamDto.getName());
        assertEquals(teamDto.getCountry(), updatedTeamDto.getCountry());

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void testUpdateWithNullId() {
        teamDto.setId(null);
        assertThrows(IllegalArgumentException.class, () -> teamService.update(teamDto));
    }

    @Test
    void testFindById() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        Optional<TeamDto> foundTeamDto = teamService.findById(1L);

        assertTrue(foundTeamDto.isPresent());
        assertEquals(teamDto.getId(), foundTeamDto.get().getId());
        assertEquals(teamDto.getName(), foundTeamDto.get().getName());
        assertEquals(teamDto.getCountry(), foundTeamDto.get().getCountry());

        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TeamDto> foundTeamDto = teamService.findById(1L);

        assertFalse(foundTeamDto.isPresent());
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        List<Team> teams = Arrays.asList(team, team);
        when(teamRepository.findAll()).thenReturn(teams);
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        List<TeamDto> foundTeams = teamService.findAll();

        assertNotNull(foundTeams);
        assertEquals(2, foundTeams.size());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        when(modelMapper.map(teamDto, Team.class)).thenReturn(team);
        doNothing().when(teamRepository).delete(team);

        teamService.delete(teamDto);

        verify(teamRepository, times(1)).delete(team);
    }

    @Test
    void testDeleteById() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteById(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByCountry() {
        List<Team> teams = Arrays.asList(team, team);
        when(teamRepository.findByCountry("Test Country")).thenReturn(teams);
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        List<TeamDto> foundTeams = teamService.findByCountry("Test Country");

        assertNotNull(foundTeams);
        assertEquals(2, foundTeams.size());
        assertEquals("Test Country", foundTeams.get(0).getCountry());
        verify(teamRepository, times(1)).findByCountry("Test Country");
    }
}