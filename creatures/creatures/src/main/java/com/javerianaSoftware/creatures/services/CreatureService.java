package com.javerianaSoftware.creatures.services;

import com.javerianaSoftware.creatures.model.Creature;
import com.javerianaSoftware.creatures.repository.CreatureRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatureService {
    private final CreatureRepository creatureRepository;
    @Autowired
    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }
    public Creature createCreature(Creature creature) {
        return creatureRepository.save(creature);
    }
    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }
    @SneakyThrows
    public Creature getCreatureById(Long id) {
        return creatureRepository.findById(id)
                .orElseThrow(() -> new Exception("Creature not found"));
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
}