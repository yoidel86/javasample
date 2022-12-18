package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.DroneDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DroneMapper {

     DroneDto toDto(final Drone drone);

    List<DroneDto> toDtos(final List<Drone> drones);

    @InheritInverseConfiguration
    Drone droneDtoToDrone( final DroneDto droneDto);

}

