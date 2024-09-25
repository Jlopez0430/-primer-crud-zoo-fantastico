package main.java.com.javerianaSoftware.creatures.controllers;

import main.java.com.javerianaSoftware.creatures.model.Zone;
import main.java.com.javerianaSoftware.creatures.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        Zone newZone = zoneService.createZone(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(newZone);
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone updatedZone) {
        Zone zone = zoneService.updateZone(id, updatedZone);
        return ResponseEntity.ok(zone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}
