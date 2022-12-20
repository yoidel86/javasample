package com.musalatest.dronetest.repository;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.types.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
    List<Drone> findByState(State state);

}
