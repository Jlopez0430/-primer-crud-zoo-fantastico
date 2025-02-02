package com.example.demo.controllers;

import com.example.demo.model.Creature;
import com.example.demo.services.CreatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService creatureService;

    @Autowired
    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    // Crear una nueva criatura
    @PostMapping
    public ResponseEntity<Creature> createCreature(@RequestBody Creature creature) {
        Creature newCreature = creatureService.createCreature(creature);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCreature);
    }

    // Obtener todas las criaturas
    @GetMapping
    public List<Creature> getAllCreatures() {
        return creatureService.getAllCreatures();
    }

    // Obtener una criatura por ID
    @GetMapping("/{id}")
    public Creature getCreatureById(@PathVariable Long id) {
        return creatureService.getCreatureById(id);
    }

    // Actualizar una criatura existente
    @PutMapping("/{id}")
    public Creature updateCreature(@PathVariable Long id, @RequestBody Creature updatedCreature) {
        return creatureService.updateCreature(id, updatedCreature);
    }

    // Eliminar una criatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreature(@PathVariable Long id) {
        creatureService.deleteCreature(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar una zona a una criatura
    @PostMapping("/{creatureId}/zones/{zoneId}")
    public ResponseEntity<String> assignZoneToCreature(@PathVariable Long creatureId, @PathVariable Long zoneId) {
        creatureService.assignZoneToCreature(creatureId, zoneId);
        return ResponseEntity.ok("Zone assigned successfully");
    }
}
