package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.LoadDto;
import com.musalatest.dronetest.model.Medication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface LoadMapper {

    @Mapping(source = "drone.id", target = "droneId",nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.SET_TO_DEFAULT  )
    @Mapping(source = "medications", target = "medicationsId")
    LoadDto toDto(final Load load);

    List<LoadDto> toDtos(final List<Load> loads);

    @InheritInverseConfiguration
    Load loadDtoToLoad( final LoadDto loadDto);


    default Integer mapMedicationToInteger(Medication medication) {
        return medication.getId();
    }
    default Medication mapMedicationIdToMedication(Integer medicationId) {
        Medication medication = new Medication();
        medication.setId(medicationId);
        return medication;
    }

    default Integer mapDroneToDroneId(Drone drone){
        if(Objects.isNull(drone)){
            return null;
        }
        return drone.getId();
    }

}

