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
import static org.mockito.ArgumentMatchers.any;
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
        TeamDto newTeamDto = new TeamDto();
        newTeamDto.setName("Test Team");
        newTeamDto.setCountry("Test Country");

        Team newTeam = new Team();
        newTeam.setName("Test Team");
        newTeam.setCountry("Test Country");

        Team savedTeam = new Team();
        savedTeam.setId(1L);
        savedTeam.setName("Test Team");
        savedTeam.setCountry("Test Country");

        when(modelMapper.map(newTeamDto, Team.class)).thenReturn(newTeam);
        when(teamRepository.save(newTeam)).thenReturn(savedTeam);
        when(modelMapper.map(savedTeam, TeamDto.class)).thenReturn(teamDto);

        TeamDto result = teamService.create(newTeamDto);

        assertNotNull(result);
        assertEquals(teamDto.getId(), result.getId());
        assertEquals(teamDto.getName(), result.getName());
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void testUpdate() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(teamRepository.save(any(Team.class))).thenReturn(team);
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        TeamDto result = teamService.update(teamDto);

        assertNotNull(result);
        assertEquals(teamDto.getId(), result.getId());
        assertEquals(teamDto.getName(), result.getName());
        verify(teamRepository, times(1)).findById(1L);
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void testFindById() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(modelMapper.map(team, TeamDto.class)).thenReturn(teamDto);

        Optional<TeamDto> foundTeam = teamService.findById(1L);

        assertTrue(foundTeam.isPresent());
        assertEquals(teamDto.getId(), foundTeam.get().getId());
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

    @Test
    void testCreateWithExistingId() {
        assertThrows(IllegalArgumentException.class, () -> teamService.create(teamDto));
    }

    @Test
    void testUpdateWithNullId() {
        TeamDto nullIdTeamDto = new TeamDto();
        assertThrows(IllegalArgumentException.class, () -> teamService.update(nullIdTeamDto));
    }
}