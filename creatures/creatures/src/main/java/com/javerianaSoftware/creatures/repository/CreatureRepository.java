package com.javerianaSoftware.creatures.repository;

import com.javerianaSoftware.creatures.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
}