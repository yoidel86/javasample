package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.TestFactory;
import com.musalatest.dronetest.mapper.DroneMapperImpl;
import com.musalatest.dronetest.mapper.LoadMapperImpl;
import com.musalatest.dronetest.mapper.MedicationMapperImpl;
import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.DroneDto;
import com.musalatest.dronetest.repository.DroneRepository;
import com.musalatest.dronetest.repository.LoadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DronesApiDelegateServiceTest {

    private static final Drone drone = TestFactory.getDrone(1);
    @Mock
    private DroneRepository droneRepository;

    @Mock
    private LoadRepository loadRepository;
//    @InjectMocks
    private DronesApiDelegateService dronesApiDelegate;
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks( this);
        dronesApiDelegate = new DronesApiDelegateService(droneRepository,loadRepository, new DroneMapperImpl(), new MedicationMapperImpl(), new LoadMapperImpl());
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
        when(droneRepository.findAll()).thenReturn(Arrays.asList(drone));
        List<Drone> drones = droneRepository.findAll();
        ResponseEntity<List<DroneDto>> listDrones = dronesApiDelegate.listDrones();
        assertNotNull(listDrones);
        assertTrue(listDrones.getBody().size()==1);
        assertTrue(listDrones.getBody().get(0).getId()==drone.getId());
    }

    @Test
    void showDroneById() {
        when(droneRepository.findById(1)).thenReturn(Optional.of(drone));
        ResponseEntity<DroneDto> droneResponse = dronesApiDelegate.showDroneById(drone.getId());
        assertNotNull(droneResponse);
        assertTrue(droneResponse.getBody().getId()==drone.getId());
        assertTrue(droneResponse.getStatusCode()== HttpStatus.OK);
    }

}
