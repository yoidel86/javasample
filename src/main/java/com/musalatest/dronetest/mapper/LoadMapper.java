package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.LoadDto;
import com.musalatest.dronetest.model.Medication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoadMapper {

    @Mapping(source = "drone.id", target = "droneId")
    @Mapping(source = "medications", target = "medicationsId")
    LoadDto toDto(final Load load);

    List<LoadDto> toDtos(final List<Load> loads);

    @InheritInverseConfiguration
    Load DroneDtoToDrone( final LoadDto loadDto);


    default Integer mapMedicationToInteger(Medication medication) {
        return medication.getId();
    }
    default Medication mapMedicationIdToMedication(Integer medicationId) {
        Medication medication = new Medication();
        medication.setId(medicationId);
        return medication;
    }

}

