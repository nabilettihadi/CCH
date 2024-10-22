package com.cch.cyclingmanager;

import com.cch.cyclingmanager.dto.CyclistDto;
import com.cch.cyclingmanager.entity.Cyclist;
import com.cch.cyclingmanager.repository.CyclistRepository;
import com.cch.cyclingmanager.service.impl.CyclistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CyclistServiceTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    private CyclistDto cyclistDto;
    private Cyclist cyclist;

    @BeforeEach
    void setUp() {
        cyclistDto = new CyclistDto();
        cyclistDto.setId(1L);
        cyclistDto.setFirstName("John");
        cyclistDto.setLastName("Doe");
        cyclistDto.setNationality("USA");

        cyclist = new Cyclist();
        cyclist.setId(1L);
        cyclist.setFirstName("John");
        cyclist.setLastName("Doe");
        cyclist.setNationality("USA");
    }

    @Test
    void testSave() {
        when(modelMapper.map(cyclistDto, Cyclist.class)).thenReturn(cyclist);
        when(cyclistRepository.save(cyclist)).thenReturn(cyclist);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        CyclistDto savedCyclistDto = cyclistService.save(cyclistDto);

        assertNotNull(savedCyclistDto);
        assertEquals(cyclistDto.getId(), savedCyclistDto.getId());
        assertEquals(cyclistDto.getFirstName(), savedCyclistDto.getFirstName());
        assertEquals(cyclistDto.getLastName(), savedCyclistDto.getLastName());
        assertEquals(cyclistDto.getNationality(), savedCyclistDto.getNationality());

        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    void testUpdate() {
        when(modelMapper.map(cyclistDto, Cyclist.class)).thenReturn(cyclist);
        when(cyclistRepository.save(cyclist)).thenReturn(cyclist);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        CyclistDto updatedCyclistDto = cyclistService.update(cyclistDto);

        assertNotNull(updatedCyclistDto);
        assertEquals(cyclistDto.getId(), updatedCyclistDto.getId());
        assertEquals(cyclistDto.getFirstName(), updatedCyclistDto.getFirstName());
        assertEquals(cyclistDto.getLastName(), updatedCyclistDto.getLastName());
        assertEquals(cyclistDto.getNationality(), updatedCyclistDto.getNationality());

        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    void testUpdateWithNullId() {
        cyclistDto.setId(null);
        assertThrows(IllegalArgumentException.class, () -> cyclistService.update(cyclistDto));
    }

    @Test
    void testFindById() {
        when(cyclistRepository.findById(1L)).thenReturn(Optional.of(cyclist));
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        Optional<CyclistDto> foundCyclistDto = cyclistService.findById(1L);

        assertTrue(foundCyclistDto.isPresent());
        assertEquals(cyclistDto.getId(), foundCyclistDto.get().getId());
        assertEquals(cyclistDto.getFirstName(), foundCyclistDto.get().getFirstName());
        assertEquals(cyclistDto.getLastName(), foundCyclistDto.get().getLastName());
        assertEquals(cyclistDto.getNationality(), foundCyclistDto.get().getNationality());

        verify(cyclistRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(cyclistRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CyclistDto> foundCyclistDto = cyclistService.findById(1L);

        assertFalse(foundCyclistDto.isPresent());
        verify(cyclistRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, cyclist);
        when(cyclistRepository.findAll()).thenReturn(cyclists);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        List<CyclistDto> foundCyclists = cyclistService.findAll();

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        when(modelMapper.map(cyclistDto, Cyclist.class)).thenReturn(cyclist);
        doNothing().when(cyclistRepository).delete(cyclist);

        cyclistService.delete(cyclistDto);

        verify(cyclistRepository, times(1)).delete(cyclist);
    }

    @Test
    void testDeleteById() {
        doNothing().when(cyclistRepository).deleteById(1L);

        cyclistService.deleteById(1L);

        verify(cyclistRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByTeamId() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, cyclist);
        when(cyclistRepository.findByTeamId(1L)).thenReturn(cyclists);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        List<CyclistDto> foundCyclists = cyclistService.findByTeamId(1L);

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findByTeamId(1L);
    }

    @Test
    void testFindAllSortedByName() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, cyclist);
        when(cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName", "firstName"))).thenReturn(cyclists);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        List<CyclistDto> foundCyclists = cyclistService.findAllSortedByName();

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "lastName", "firstName"));
    }

    @Test
    void testFindAllSortedByNationality() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, cyclist);
        when(cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "nationality"))).thenReturn(cyclists);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        List<CyclistDto> foundCyclists = cyclistService.findAllSortedByNationality();

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "nationality"));
    }

    @Test
    void testFindAllSortedByTeam() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, cyclist);
        when(cyclistRepository.findAll(Sort.by(Sort.Direction.ASC, "team.name"))).thenReturn(cyclists);
        when(modelMapper.map(cyclist, CyclistDto.class)).thenReturn(cyclistDto);

        List<CyclistDto> foundCyclists = cyclistService.findAllSortedByTeam();

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "team.name"));
    }
}