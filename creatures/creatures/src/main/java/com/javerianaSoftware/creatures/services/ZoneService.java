package com.javerianaSoftware.creatures.services;

import com.javerianaSoftware.creatures.model.Zone;
import com.javerianaSoftware.creatures.repository.ZoneRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;
    @Autowired
    public ZoneService(ZoneRepository zoneRepository, ZoneRepository zoneRepository1) {
        this.zoneRepository = zoneRepository;
    }
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
    @SneakyThrows
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new Exception("Zone not found"));
    }
    public Zone updateZone(Long id, Zone updatedZone) {
        Zone zone = getZoneById(id);
        zone.setName(updatedZone.getName());
        zone.setLocation(updatedZone.getLocation());
        return zoneRepository.save(zone);
    }
    public void deleteZone(Long id) {
        Zone zone = getZoneById(id);
    }
}