package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.TestFactory;
import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.DroneDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DroneMapperTest {

    @Spy
    private DroneMapper droneMapper = Mappers.getMapper(DroneMapper.class);

    @Test
    void toDto() {
        Drone drone = TestFactory.getDrone();
        DroneDto dto = droneMapper.toDto(drone);
        assertTrue(dto.getId()==drone.getId());
        assertTrue(Objects.equals(dto.getSerialNumber(), drone.getSerialNumber()));
        assertTrue(dto.getBatteryCapacity()==drone.getBatteryCapacity());
        assertTrue(dto.getWeightLimit()==drone.getWeightLimit());
    }
    @Test
    void toDtoModelEnum() {
        Drone drone = TestFactory.getDrone();
        DroneDto dto = droneMapper.toDto(drone);
        assertTrue(Objects.equals(dto.getModel(), String.valueOf(drone.getModel())));
    }

    @Test
    void toDtos() {
        List<Drone> droneList = new ArrayList<>();
        droneList.add(TestFactory.getDrone(1));
        droneList.add(TestFactory.getDrone(2));
        droneList.add(TestFactory.getDrone(3));
        List<DroneDto> droneDtos = droneMapper.toDtos(droneList);

        assertTrue(droneDtos.size()==droneList.size());
        assertTrue(droneDtos.get(0).getId()==1);
        assertTrue(droneDtos.get(2).getId()==3);
    }

    @Test
    void droneDtoToDrone() {
        Drone drone = TestFactory.getDrone();
        DroneDto dto = droneMapper.toDto(drone);
        Drone droneReversed = droneMapper.droneDtoToDrone(dto);

        assertTrue(droneReversed.getId()==drone.getId());
        assertTrue(Objects.equals(droneReversed.getSerialNumber(), drone.getSerialNumber()));
        assertTrue(droneReversed.getBatteryCapacity()==drone.getBatteryCapacity());
        assertTrue(droneReversed.getWeightLimit()==drone.getWeightLimit());
    }
}
