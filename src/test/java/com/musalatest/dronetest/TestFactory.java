package com.musalatest.dronetest;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.Medication;
import com.musalatest.dronetest.model.types.Model;

import java.util.ArrayList;
import java.util.List;

public class TestFactory {


 //basic drone info
 private static final String SERIAL_NUMBER = "SN12345";
 private static final Integer BATTERY_CAPACITY = 20;
 private static final Integer ID = 35;
 private static final Integer WEIGHT_LIMIT = 350;
 private static String MODEL = String.valueOf(Model.Lightweight);


 public static Drone getDrone() {
  return getDrone(ID);
 }
 public static Drone getDrone(int id){
  final Drone drone = new Drone();
  drone.setId(id);
  drone.setSerialNumber(SERIAL_NUMBER);
  drone.setBatteryCapacity(BATTERY_CAPACITY);
  drone.setWeightLimit(WEIGHT_LIMIT);
  drone.setModel(Model.valueOf(MODEL));
  return drone;
 }

 public static Medication getMedication() {
  return getMedication(1);
 }
 public static Medication getMedication(int id) {
  final Medication med = new Medication();
  med.setCode("CODE1");
  med.setId(id);
  med.setName("MED1");
  med.setWeight(20);
  med.setImage("IMAGE1");
  return med;
 }

 public static Load getLoad() {
  return getLoad(1);
 }
 public static Load getLoad(int id){
  Load load = new Load();
  load.setId(id);
  load.setDrone(getDrone());
  List<Medication> medications = new ArrayList<>();
  medications.add(TestFactory.getMedication(1));
  medications.add(TestFactory.getMedication(2));
  load.setMedications(medications);
  return load;

 }
}
