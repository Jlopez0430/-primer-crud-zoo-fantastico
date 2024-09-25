package main.java.com.javerianaSoftware.creatures.services;

import main.java.com.javerianaSoftware.creatures.model.Zone;
import main.java.com.javerianaSoftware.creatures.repository.ZoneRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zone getZoneById(Long id) {
        try {
            return zoneRepository.findById(id)
                    .orElseThrow(() -> new Exception("Zone not found"));
        } catch (Exception e) {
            e.printStackTrace(); // Puedes registrar el error o realizar otra acción.
            return null; // Puedes retornar null o lanzar otra excepción según lo que prefieras.
        }
    }

    public Zone updateZone(Long id, Zone updatedZone) {
        Zone zone = getZoneById(id);
        if (zone != null) {
            zone.setName(updatedZone.getName());
            zone.setLocation(updatedZone.getLocation());
        }
        return zoneRepository.save(zone);
    }

    public void deleteZone(Long id) {
        Zone zone = getZoneById(id);
        if (zone != null) {
            zoneRepository.delete(zone);
        }
    }
}
