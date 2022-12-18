package com.musalatest.dronetest.delegate;

import com.google.common.collect.ImmutableMap;
import com.musalatest.dronetest.DronesApiDelegate;
import com.musalatest.dronetest.exception.DronetestException;
import com.musalatest.dronetest.exception.EntityNotFoundException;
import com.musalatest.dronetest.mapper.DroneMapper;
import com.musalatest.dronetest.mapper.LoadMapper;
import com.musalatest.dronetest.mapper.MedicationMapper;
import com.musalatest.dronetest.model.*;
import com.musalatest.dronetest.repository.DroneRepository;
import com.musalatest.dronetest.repository.LoadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DronesApiDelegateService implements DronesApiDelegate {
    final DroneRepository droneRepository;
    final LoadRepository loadRepository;
    final DroneMapper droneMapper;
    final MedicationMapper medicationMapper;
    final LoadMapper loadMapper;

    public DronesApiDelegateService(DroneRepository droneRepository, LoadRepository loadRepository, DroneMapper droneMapper, MedicationMapper medicationMapper, LoadMapper loadMapper) {
        this.droneRepository = droneRepository;
        this.loadRepository = loadRepository;
        this.droneMapper = droneMapper;
        this.medicationMapper = medicationMapper;
        this.loadMapper = loadMapper;
    }

    public ResponseEntity<List<DroneDto>> listDrones() {
        List<Drone> drones = droneRepository.findAll();
        List<DroneDto> result = droneMapper.toDtos(drones);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DroneDto> createDrone(DroneDto droneDto) {
       Drone drone = droneMapper.droneDtoToDrone(droneDto);
       drone = droneRepository.save(drone);
       DroneDto result = droneMapper.toDto(drone);
       return ResponseEntity.ok(result);
    }
    public ResponseEntity<DroneDto> showDroneById(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        DroneDto result = droneMapper.toDto(drone);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> getDroneBateryLevelById(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        return ResponseEntity.ok(String.format("%d %s",drone.getBatteryCapacity(),"%"));
    }

    public  ResponseEntity<List<MedicationDto>> getDroneLoadedMedicationsById(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        Load load = drone.getCurrentLoad();
        if(load==null){
            throw new DronetestException(HttpStatus.NOT_FOUND,"Drone is not loaded");
        }
        List<MedicationDto> medicationDtos = medicationMapper.toDtos(load.getMedications());
        return ResponseEntity.ok(medicationDtos);
    }

    public ResponseEntity<DroneLoadedResponseDto> setDroneToLoad(Integer droneId,
                                                          Integer loadId){
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new EntityNotFoundException(Load.class, ImmutableMap.of("loadId",loadId)));
        //@Todo Implements validations
        drone.setCurrentLoad(load);
        droneRepository.save(drone);
        DroneLoadedResponseDto result = new DroneLoadedResponseDto();
        result.setDrone(droneMapper.toDto(drone));
        result.setLoad(loadMapper.toDto(load));
        return ResponseEntity.ok(result);
    }

}
