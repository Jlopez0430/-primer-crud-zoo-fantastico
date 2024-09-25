package main.java.com.javerianaSoftware.creatures.repository;

import main.java.com.javerianaSoftware.creatures.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
}