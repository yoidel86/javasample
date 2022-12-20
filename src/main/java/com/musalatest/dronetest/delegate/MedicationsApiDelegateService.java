package com.musalatest.dronetest.delegate;

import com.google.common.collect.ImmutableMap;
import com.musalatest.dronetest.MedicationsApiDelegate;
import com.musalatest.dronetest.exception.EntityNotFoundException;
import com.musalatest.dronetest.mapper.MedicationMapper;
import com.musalatest.dronetest.model.Medication;
import com.musalatest.dronetest.model.MedicationDto;
import com.musalatest.dronetest.repository.MedicationRepository;
import com.musalatest.dronetest.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MedicationsApiDelegateService implements MedicationsApiDelegate {
    final MedicationRepository medicationRepository;
    final MedicationMapper medicationMapper;


    public MedicationsApiDelegateService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

     public ResponseEntity<List<MedicationDto>> listMedications() {
         log.info("getting all medications");
        List<Medication> medications = medicationRepository.findAll();
        List<MedicationDto> result = medicationMapper.toDtos(medications);
        return ResponseEntity.ok(result);
    }
    public ResponseEntity<MedicationDto> createMedication(MedicationDto medicationDto) {
        log.info("create a medication");
        Medication medication = medicationMapper.medicationDtoToMedication(medicationDto);
        medication = medicationRepository.save(medication);
        MedicationDto result = medicationMapper.toDto(medication);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<MedicationDto> showMedicationById(Integer medicationId) {
        log.info("show a medication");
        Medication medication= medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException(Medication.class, ImmutableMap.of("medicationId",medicationId)));
        MedicationDto result = medicationMapper.toDto(medication);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<MedicationDto> updateMedications(Integer medicationId,
                                                           MedicationDto medicationDto) {
        log.info("update a medication");
        Medication oldMedication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException(Medication.class, ImmutableMap.of("medicationId",medicationId)));
        Medication medication = medicationMapper.medicationDtoToMedication(medicationDto);
        Utils.copyNonNullProperties(medication,oldMedication);
        oldMedication = medicationRepository.save(oldMedication);
        MedicationDto result = medicationMapper.toDto(oldMedication);
        return ResponseEntity.ok(result);
    }

}
