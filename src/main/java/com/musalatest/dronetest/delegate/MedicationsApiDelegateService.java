package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.MedicationsApiDelegate;
import com.musalatest.dronetest.mapper.MedicationMapper;
import com.musalatest.dronetest.model.Medication;
import com.musalatest.dronetest.model.MedicationDto;
import com.musalatest.dronetest.repository.MedicationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationsApiDelegateService implements MedicationsApiDelegate {
    final MedicationRepository medicationRepository;
    final MedicationMapper medicationMapper;


    public MedicationsApiDelegateService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

     public ResponseEntity<List<MedicationDto>> listMedications() {
        List<Medication> medications = medicationRepository.findAll();
        List<MedicationDto> result = medicationMapper.toDtos(medications);
        return ResponseEntity.ok(result);
    }
    public ResponseEntity<MedicationDto> createMedication(MedicationDto medicationDto) {
        Medication medication = medicationMapper.medicationDtoToMedication(medicationDto);
        medication = medicationRepository.save(medication);
        MedicationDto result = medicationMapper.toDto(medication);
        return ResponseEntity.ok(result);
    }

}
