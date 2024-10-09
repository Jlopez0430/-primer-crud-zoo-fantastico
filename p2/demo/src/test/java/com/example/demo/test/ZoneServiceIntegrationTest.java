package com.example.demo.test;

import com.example.demo.model.Creature;
import com.example.demo.model.Zone;
import com.example.demo.repository.CreatureRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.services.CreatureService;
import com.example.demo.services.ZoneService;
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
public class ZoneServiceIntegrationTest {
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private ZoneRepository zoneRepository;
    @Test
    void testCreateZone_ShouldPersistInDatabase() {
        Zone zone = new Zone();
        zone.setName("Zona norte");
        zone.setLocation("norte");
        zoneService.createZone(zone);
        Optional<Zone> foundZone = zoneRepository
                .findById(zone.getId());
        assertTrue(foundZone.isPresent());
        assertEquals("Zona norte", foundZone.get().getName());
    }

    @Test
    void testUpdateZone_ShouldPersistInDatabase() {
        Zone zone = new Zone();
        zone.setName("Zona norte");
        zone.setLocation("norte");
        zoneService.createZone(zone);
        Optional<Zone> foundZone = zoneRepository
                .findById(zone.getId());
        foundZone.get().setName("Parte norte");
        assertTrue(foundZone.isPresent());
        assertEquals("Parte norte", foundZone.get().getName());
    }

    @Test
    void testDeleteZone_ShouldPersistInDatabase() {
        Zone zone = new Zone();
        zone.setName("Zona norte");
        zone.setLocation("norte");
        zone.setId(1L);
        zoneService.createZone(zone);
        Optional<Zone> foundZone = zoneRepository
                .findById(zone.getId());

        if(foundZone!=null) {
            zoneService.deleteZone(zone.getId());
        }
    }
}
