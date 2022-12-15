package com.musalatest.dronetest.model;

import com.musalatest.dronetest.model.types.Model;
import com.musalatest.dronetest.model.types.State;

import javax.persistence.*;


//  Drone definition
//        - serial number (100 characters max);
//        - model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
//        - weight limit (500gr max);
//        - battery capacity (percentage);
//        - state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String serialNumber;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Model model;

    @Column
    private int weightLimit;

    @Column
    private int batteryCapacity;

    @Column
    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "load_id")
    private Load currentLoad;
}
