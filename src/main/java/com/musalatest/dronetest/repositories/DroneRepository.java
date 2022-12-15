package com.musalatest.dronetest.repositories;

import com.musalatest.dronetest.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
