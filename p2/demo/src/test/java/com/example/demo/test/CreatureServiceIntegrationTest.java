package com.example.demo.test;

// pruebas conjuntas

import com.example.demo.model.Creature;
import com.example.demo.repository.CreatureRepository;
import com.example.demo.services.CreatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreatureServiceIntegrationTest {
    @Autowired
    private CreatureService creatureService;
    @Autowired
    private CreatureRepository creatureRepository;
    @Test
    void testCreateCreature_ShouldPersistInDatabase() {
        Creature creature = new Creature();
        creature.setName("Unicornio");
        creature.setDangerLevel(5);
        creatureService.createCreature(creature);
        Optional<Creature> foundCreature = creatureRepository
                .findById(creature.getId());
        assertTrue(foundCreature.isPresent());
        assertEquals("Unicornio", foundCreature.get().getName());
    }

    @Test
    void testUpdateCreature_ShouldPersistInDatabase() {
        Creature creature = new Creature();
        creature.setName("rinoceronte");
        creature.setDangerLevel(5);
        creatureService.createCreature(creature);
        Optional<Creature> foundCreature = creatureRepository
                .findById(creature.getId());
        foundCreature.get().setName("Unicornio gordito");
        assertTrue(foundCreature.isPresent());
        assertEquals("Unicornio gordito", foundCreature.get().getName());
    }

    @Test
    void testDeleteCreature_ShouldPersistInDatabase() {
        Creature creature = new Creature();
        creature.setName("Unicornio");
        creature.setHealthStatus("normal");
        creatureService.createCreature(creature);
        Optional<Creature> foundCreature = creatureRepository
                .findById(creature.getId());

        String valor = foundCreature.get().getHealthStatus();
        if(!valor.equals("critical")) {
            creatureService.deleteCreature(creature.getId());
        }
    }


}