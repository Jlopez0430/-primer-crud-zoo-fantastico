    package com.example.demo.test;

    import com.example.demo.model.Creature;
    import com.example.demo.model.Zone;
    import com.example.demo.repository.CreatureRepository;
    import com.example.demo.repository.ZoneRepository;
    import com.example.demo.services.CreatureService;
    import com.example.demo.services.ZoneService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.*;

    public class ZoneTest {
        @Mock
        private ZoneRepository zoneRepository;

        @InjectMocks
        private ZoneService zoneService;
        @BeforeEach
        void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void testCreateZone_ShouldReturnSavedZone() {
            Zone zone = new Zone();
            zone.setId(1L);
            zone.setName("zona sur");
            zone.setLocation("sur");
            when(zoneRepository.save(any(Zone.class))).thenReturn(zone);
            Zone savedZone = zoneService.createZone(zone);
            assertNotNull(savedZone);
            assertEquals("zona sur", savedZone.getName());
        }

        @Test
        void testGetZoneById_ShouldReturnZone() {
            Zone zone = new Zone();
            zone.setId(1L);
            zone.setName("zona norte");
            zone.setLocation("norte");
            when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
            Zone foundZone = zoneService.getZoneById(1L);
            assertNotNull(foundZone);
            assertEquals("zona norte", foundZone.getName());
            assertEquals(1L, foundZone.getId());
        }

        @Test
        void testUpdateZone_ShouldReturnUpdatedZone() {
            Zone zone = new Zone();
            zone.setId(1L);
            zone.setLocation("oeste");
            when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
            Zone foundZone = zoneService.getZoneById(1L);
            assertNotNull(foundZone);
            foundZone.setName("zona oeste");
            when(zoneRepository.save(any(Zone.class))).thenReturn(foundZone);
            Zone savedZone = zoneService.updateZone(1L, foundZone);
            assertEquals("zona oeste", savedZone.getName());
            assertEquals( 1L, savedZone.getId());
            assertEquals("oeste", savedZone.getLocation());
        }

        @Test
        void testDeleteCreature_ShouldDeleteCreature() {
            Zone zone = new Zone();
            zone.setId(1L);
            zone.setLocation("oeste");

            when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));

            Zone foundZone = zoneService.getZoneById(1L);
            if (zone != null) {
                zoneRepository.delete(foundZone);
            } else {
                throw new IllegalStateException("Cannot delete a zone that is not empty");
            }
            verify(zoneRepository, times(1)).delete(zone);
        }
    }
