package com.musalatest.dronetest.model;

import com.musalatest.dronetest.model.types.Model;
import com.musalatest.dronetest.model.types.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Load getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(Load currentLoad) {
        this.currentLoad = currentLoad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
