package main.java.com.javerianaSoftware.creatures.repository;

import main.java.com.javerianaSoftware.creatures.model.Creature;
import main.java.com.javerianaSoftware.creatures.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}