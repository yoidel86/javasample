package com.musalatest.dronetest.repositories;

import com.musalatest.dronetest.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
