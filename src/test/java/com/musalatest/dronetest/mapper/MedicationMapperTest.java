package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.TestFactory;
import com.musalatest.dronetest.model.Medication;
import com.musalatest.dronetest.model.MedicationDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicationMapperTest {

    @Spy
    private MedicationMapper medicationMapper = Mappers.getMapper(MedicationMapper.class);

    @Test
    void toDto() {
        Medication medication = TestFactory.getMedication();
        MedicationDto dto = medicationMapper.toDto(medication);
        assertTrue(dto.getId()==medication.getId());
        assertTrue(Objects.equals(dto.getCode(), medication.getCode()));
        assertTrue(Objects.equals(dto.getImage(), medication.getImage()));
        assertTrue(Objects.equals(dto.getName(), medication.getName()));
        assertTrue(dto.getWeight()==medication.getWeight());
    }

    @Test
    void toDtos() {
        List<Medication> medications = new ArrayList<>();
        medications.add(TestFactory.getMedication(1));
        medications.add(TestFactory.getMedication(2));
        medications.add(TestFactory.getMedication(3));
        List<MedicationDto> medicationDtos = medicationMapper.toDtos(medications);

        assertTrue(medicationDtos.size()==medications.size());
        assertTrue(medicationDtos.get(0).getId()==1);
        assertTrue(medicationDtos.get(2).getId()==3);

    }

    @Test
    void medicationDtoToMedication() {
        Medication medication = TestFactory.getMedication();
        MedicationDto dto = medicationMapper.toDto(medication);
        Medication medicationReversed = medicationMapper.medicationDtoToMedication(dto);

        assertTrue(medicationReversed.getId()==medication.getId());
        assertTrue(Objects.equals(medicationReversed.getCode(), medication.getCode()));
        assertTrue(medicationReversed.getWeight()==medication.getWeight());
        assertTrue(Objects.equals(medicationReversed.getImage(),medication.getImage()));
}





}
