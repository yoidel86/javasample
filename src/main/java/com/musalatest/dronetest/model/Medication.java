package com.musalatest.dronetest.model;

import javax.persistence.*;
import java.util.List;


//  Medication definition
//        - name (allowed only letters, numbers, ‘-‘, ‘_’);
//        - weight;
//        - code (allowed only upper case letters, underscore and numbers);
//        - image (picture of the medication case).

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Integer weight;

    @Column
    private String image;

    @ManyToMany
    private List<Load> loaded;

}
