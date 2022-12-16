package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.DronesApiDelegate;
import com.musalatest.dronetest.mapper.DroneMapper;
import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.DroneDto;
import com.musalatest.dronetest.repository.DroneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DronesApiDelegateService implements DronesApiDelegate {
    final DroneRepository droneRepository;
    final DroneMapper droneMapper;

    public DronesApiDelegateService(DroneRepository droneRepository, DroneMapper droneMapper) {
        this.droneRepository = droneRepository;
        this.droneMapper = droneMapper;
    }

    public ResponseEntity<List<DroneDto>> listDrones() {
        List<Drone> drones = droneRepository.findAll();
        List<DroneDto> result = droneMapper.toDtos(drones);
        return ResponseEntity.ok(result);
    }
}
