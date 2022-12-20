package com.musalatest.dronetest.repository;

import com.musalatest.dronetest.model.Checks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecksRepository extends JpaRepository<Checks, Integer> {
}
