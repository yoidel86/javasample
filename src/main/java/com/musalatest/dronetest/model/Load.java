package com.musalatest.dronetest.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToMany(fetch=EAGER)
    private List<Medication> medications;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Integer getTotalWeight(){
        int total_weight = 0;
        for (Medication medication :
                this.getMedications()) {
            total_weight += medication.getWeight();
        }
        return total_weight;
    }
}
