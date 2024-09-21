package com.javerianaSoftware.creatures.repository;

import com.javerianaSoftware.creatures.model.Creature;
import com.javerianaSoftware.creatures.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}