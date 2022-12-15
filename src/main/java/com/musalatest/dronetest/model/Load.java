package com.musalatest.dronetest.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToMany(mappedBy = "loaded")
    private List<Medication> medications;


}
