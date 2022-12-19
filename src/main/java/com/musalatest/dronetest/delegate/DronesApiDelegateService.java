package com.musalatest.dronetest.delegate;

import com.google.common.collect.ImmutableMap;
import com.musalatest.dronetest.DronesApiDelegate;
import com.musalatest.dronetest.exception.DronetestException;
import com.musalatest.dronetest.exception.EntityNotFoundException;
import com.musalatest.dronetest.mapper.DroneMapper;
import com.musalatest.dronetest.mapper.LoadMapper;
import com.musalatest.dronetest.mapper.MedicationMapper;
import com.musalatest.dronetest.model.*;
import com.musalatest.dronetest.model.types.State;
import com.musalatest.dronetest.repository.DroneRepository;
import com.musalatest.dronetest.repository.LoadRepository;
import com.musalatest.dronetest.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
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

    final Validator validator;
    final LoadMapper loadMapper;

    @Value("${validations.battery.min}")
    long min_battery_level;

    public DronesApiDelegateService(DroneRepository droneRepository, LoadRepository loadRepository, DroneMapper droneMapper, MedicationMapper medicationMapper, Validator validator, LoadMapper loadMapper) {
        this.droneRepository = droneRepository;
        this.loadRepository = loadRepository;
        this.droneMapper = droneMapper;
        this.medicationMapper = medicationMapper;
        this.validator = validator;
        this.loadMapper = loadMapper;
    }

    public ResponseEntity<List<DroneDto>> listDrones(String state) {
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

    public ResponseEntity<InlineResponse200Dto> getDroneBateryLevelById(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        InlineResponse200Dto response = new InlineResponse200Dto();
        response.setBattery(drone.getBatteryCapacity());
        return ResponseEntity.ok(response);
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
        System.out.println(min_battery_level);

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(Drone.class, ImmutableMap.of("droneId",droneId)));
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new EntityNotFoundException(Load.class, ImmutableMap.of("loadId",loadId)));
        List<String> errors = validator.isValid(load);
        if (!errors.isEmpty()) {
            throw new DronetestException(HttpStatus.BAD_REQUEST,"INVALID LOAD",errors);
        }
        errors = validator.isValidForLoad(drone,load.getTotalWeight());
        if (!errors.isEmpty()){
            throw new DronetestException(HttpStatus.BAD_REQUEST,"INVALID DRONE",errors);
        }
        drone.setCurrentLoad(load);
        drone.setState(State.LOADING);
        drone = droneRepository.save(drone);
        load.setDrone(drone);
        load = loadRepository.save(load);
        DroneLoadedResponseDto result = new DroneLoadedResponseDto();
        result.setDrone(droneMapper.toDto(drone));
        result.setLoad(loadMapper.toDto(load));
        return ResponseEntity.ok(result);
    }

}
