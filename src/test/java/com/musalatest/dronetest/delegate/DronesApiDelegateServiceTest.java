package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.TestFactory;
import com.musalatest.dronetest.exception.DronetestException;
import com.musalatest.dronetest.mapper.DroneMapperImpl;
import com.musalatest.dronetest.mapper.LoadMapperImpl;
import com.musalatest.dronetest.mapper.MedicationMapperImpl;
import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.DroneDto;
import com.musalatest.dronetest.model.DroneLoadedResponseDto;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.repository.DroneRepository;
import com.musalatest.dronetest.repository.LoadRepository;
import com.musalatest.dronetest.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@SpringBootConfiguration
@TestPropertySource(properties = {
        "validations.batery.min=25",
})
class DronesApiDelegateServiceTest {

    @Mock
    private DroneRepository droneRepository;
    @Value("${validations.batery.min}")
    int min_battery_level;
    @Mock
    private LoadRepository loadRepository;
//    @InjectMocks
    private DronesApiDelegateService dronesApiDelegate;
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks( this);
        dronesApiDelegate = new DronesApiDelegateService(droneRepository,loadRepository, new DroneMapperImpl(), new MedicationMapperImpl(), new Validator(min_battery_level), new LoadMapperImpl());
    }

    @Test
    public void testValueSetup() {
        assertEquals(25, min_battery_level);
    }

    @Test
    void getRequest() {

    }

    @Test
    void createDrone() {
    }

    @Test
    void getDroneBateryLevelById() {
    }

    @Test
    void getDroneLoadedMedicationsById() {
    }

    @Test
    void listDrones() {
        Drone drone = TestFactory.getDrone(1);
        when(droneRepository.findAll()).thenReturn(Arrays.asList(drone));
        List<Drone> drones = droneRepository.findAll();
        ResponseEntity<List<DroneDto>> listDrones = dronesApiDelegate.listDrones("");
        assertNotNull(listDrones);
        assertTrue(listDrones.getBody().size()==1);
        assertTrue(listDrones.getBody().get(0).getId()==drone.getId());
    }

    @Test
    void showDroneById() {
        Drone drone = TestFactory.getDrone(1);
        when(droneRepository.findById(1)).thenReturn(Optional.of(drone));
        ResponseEntity<DroneDto> droneResponse = dronesApiDelegate.showDroneById(drone.getId());
        assertNotNull(droneResponse);
        assertTrue(droneResponse.getBody().getId()==drone.getId());
        assertTrue(droneResponse.getStatusCode()== HttpStatus.OK);
    }

    @Test
    void testInvalidStateDroneToLoad() {
        Drone drone = TestFactory.getDroneDelivering(1);
        Load load = TestFactory.getLoad(1);
        when(droneRepository.findById(1)).thenReturn(Optional.of(drone));
        when(loadRepository.findById(1)).thenReturn(Optional.of(load));
        //Test validation of invalid state exception
        DronetestException exception = assertThrows(DronetestException.class,()->dronesApiDelegate.setDroneToLoad(drone.getId(),load.getId()));
        assertTrue(Objects.equals(exception.getMessage(), "INVALID DRONE"));
    }
    @Test
    void testInvalidBateryDroneToLoad() {
        Drone drone = TestFactory.getDroneLowBatery(1);
        Load load = TestFactory.getLoad(1);
        when(droneRepository.findById(1)).thenReturn(Optional.of(drone));
        when(loadRepository.findById(1)).thenReturn(Optional.of(load));
        //Test validation of invalid state exception
        DronetestException exception = assertThrows(DronetestException.class,()->dronesApiDelegate.setDroneToLoad(drone.getId(),load.getId()));
//        System.out.println(exception.getMessage());
        assertTrue(Objects.equals(exception.getMessage(), "INVALID DRONE"));
    }

    @Test
    void testDroneToLoad() {
        Drone drone = TestFactory.getDrone(1);
        Load load = TestFactory.getLoad(1);
        when(droneRepository.findById(1)).thenReturn(Optional.of(drone));
        when(loadRepository.findById(1)).thenReturn(Optional.of(load));
        //Test validation of invalid state exception
        ResponseEntity<DroneLoadedResponseDto> response= dronesApiDelegate.setDroneToLoad(drone.getId(),load.getId());
//        System.out.println(exception.getMessage());

    }
    @Configuration
    static class Config {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer();
        }

    }
}
