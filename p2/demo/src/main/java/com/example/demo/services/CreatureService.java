package com.example.demo.services;

import com.example.demo.model.Creature;
import com.example.demo.model.Zone;
import com.example.demo.repository.CreatureRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatureService {
    private final CreatureRepository creatureRepository;
    private final ZoneRepository zoneRepository;

    @Autowired
    public CreatureService(CreatureRepository creatureRepository, ZoneRepository zoneRepository     ) {
        this.creatureRepository = creatureRepository; this.zoneRepository = zoneRepository;
    }

    public Creature createCreature(Creature creature) {
        return creatureRepository.save(creature);
    }

    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }

    public Creature getCreatureById(Long id) {
        try {
            return creatureRepository.findById(id)
                    .orElseThrow(() -> new Exception("Creature not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Puedes lanzar una excepción personalizada o retornar `null`.
        }
    }

    public Creature updateCreature(Long id, Creature updatedCreature) {
        Creature creature = getCreatureById(id);
        creature.setName(updatedCreature.getName());
        creature.setSpecies(updatedCreature.getSpecies());
        creature.setSize(updatedCreature.getSize());
        creature.setDangerLevel(updatedCreature.getDangerLevel());
        creature.setHealthStatus(updatedCreature.getHealthStatus());
        return creatureRepository.save(creature);
    }

    public void deleteCreature(Long id) {
        Creature creature = getCreatureById(id);
        if (!"critical".equals(creature.getHealthStatus())) {
            creatureRepository.delete(creature);
        } else {
            throw new IllegalStateException("Cannot delete a creature in critical health");
        }
    }

    public Creature assignZoneToCreature(Long creatureId, Long zoneId) {
        Optional<Creature> creatureOptional = creatureRepository.findById(creatureId);
        if (!creatureOptional.isPresent()) {
            throw new RuntimeException("Creature not found");
        }
        Creature creature = creatureOptional.get();

        Optional<Zone> zoneOptional = zoneRepository.findById(zoneId);
        if (!zoneOptional.isPresent()) {
            throw new RuntimeException("Zone not found");
        }
        Zone zone = zoneOptional.get();

        creature.setZone(zone);
        return creatureRepository.save(creature);
    }


}
