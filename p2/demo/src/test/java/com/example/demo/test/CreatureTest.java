package com.example.demo.test;

import com.example.demo.model.Creature;
import com.example.demo.model.Zone;
import com.example.demo.repository.CreatureRepository;
import com.example.demo.services.CreatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreatureTest {
    @Mock
    private CreatureRepository creatureRepository;

    @InjectMocks
    private CreatureService creatureService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCreature_ShouldReturnSavedCreature() {
        Creature creature = new Creature();
        creature.setName("Fénix");
        when(creatureRepository.save(any(Creature.class))).thenReturn(creature);
        Creature savedCreature = creatureService.createCreature(creature);
        assertNotNull(savedCreature);
        assertEquals("Fénix", savedCreature.getName());
    }


    @Test
    void testGetCreatureById_ShouldReturnCreature() {
        Creature creature = new Creature();
        creature.setName("Dinosaur");
        creature.setId(1L);
        when(creatureRepository.findById(1L)).thenReturn(Optional.of(creature));
        Creature foundCreature = creatureService.getCreatureById(1L);
        assertNotNull(foundCreature);
        assertEquals("Dinosaur", foundCreature.getName());
        assertEquals(1L, foundCreature.getId());
    }

    @Test
    void testUpdateCreature_ShouldReturnUpdatedCreature() {
        Creature creature = new Creature();
        creature.setName("Dinosaur");
        creature.setId(2L);
        when(creatureRepository.findById(2L)).thenReturn(Optional.of(creature));
        Creature foundCreature = creatureService.getCreatureById(2L);
        assertNotNull(foundCreature);
        foundCreature.setDangerLevel(10);
        when(creatureRepository.save(any(Creature.class))).thenReturn(foundCreature);
        Creature savedCreature = creatureService.updateCreature(2L, foundCreature);
        assertEquals("Dinosaur", savedCreature.getName());
        assertEquals( 2L, savedCreature.getId());
        assertEquals(10, savedCreature.getDangerLevel());
    }

    @Test
    void testDeleteCreature_ShouldDeleteCreature() {
        Creature creature = new Creature();
        creature.setName("Triceraptops");
        creature.setId(3L);
        creature.setHealthStatus("normal");

        when(creatureRepository.findById(3L)).thenReturn(Optional.of(creature));

        Creature foundCreature = creatureService.getCreatureById(3L);
        if (!"critical".equals(foundCreature.getHealthStatus())) {
            creatureRepository.deleteById(foundCreature.getId());

        } else {
            throw new IllegalStateException("Cannot delete a creature in critical health");
        }
        verify(creatureRepository, times(1)).deleteById(3L);

    }

    @Test
    void testAddZoneCreature_ShouldReturnCreatureWithZone() {
        Creature creature = new Creature();
        Zone zone = new Zone();
        zone.setId(1L);
        zone.setLocation("norte");
        creature.setName("Dinosaur");
        creature.setId(4L);
        creature.setZone(zone);
        when(creatureRepository.findById(4L)).thenReturn(Optional.of(creature));
        Creature foundCreature = creatureService.getCreatureById(4L);
        assertNotNull(foundCreature);
        assertEquals("Dinosaur", foundCreature.getName());
        assertEquals(4L, foundCreature.getId());
        assertEquals(zone.getId(), foundCreature.getZone().getId());
    }


}
