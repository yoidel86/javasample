package com.musalatest.dronetest.delegate;

import com.google.common.collect.ImmutableMap;
import com.musalatest.dronetest.LoadsApiDelegate;
import com.musalatest.dronetest.exception.EntityNotFoundException;
import com.musalatest.dronetest.mapper.LoadMapper;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.LoadDto;
import com.musalatest.dronetest.model.Medication;
import com.musalatest.dronetest.repository.LoadRepository;
import com.musalatest.dronetest.repository.MedicationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadsApiDelegateService implements LoadsApiDelegate {
    final LoadRepository loadRepository;
    final MedicationRepository medicationRepository;
    final LoadMapper loadMapper;

    public LoadsApiDelegateService(LoadRepository loadRepository, MedicationRepository medicationRepository, LoadMapper loadMapper) {
        this.loadRepository = loadRepository;
        this.medicationRepository = medicationRepository;
        this.loadMapper = loadMapper;
    }

    public ResponseEntity<List<LoadDto>> listLoads() {
        List<Load> loads =  loadRepository.findAll();
        List<LoadDto> result = loadMapper.toDtos(loads);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<LoadDto> createLoad(LoadDto loadDto) {
        Load load = loadMapper.loadDtoToLoad(loadDto);
        //if this load don't use drone yet set it to null
        if(load.getDrone().getId()==null){
            load.setDrone(null);
        }
        List<Medication> medications = new ArrayList<>();
        load.setMedications(medications);//save before set medications to have id for many to many relations
        load = loadRepository.save(load);
        for(Integer id:loadDto.getMedicationsId()){
            medications.add(medicationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Medication.class, ImmutableMap.of("medicationId",id))));
        }
        load.setMedications(medications);
        load = loadRepository.save(load);//save relation with medications
        LoadDto result = loadMapper.toDto(load);
        return ResponseEntity.ok(result);


    }
}
