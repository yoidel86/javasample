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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Load> getLoaded() {
        return loaded;
    }

    public void setLoaded(List<Load> loaded) {
        this.loaded = loaded;
    }
}
