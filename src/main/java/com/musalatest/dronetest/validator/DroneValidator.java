package com.musalatest.dronetest.validator;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.types.State;


public enum DroneValidator {
    STATE() {
        public String validateForLoad(Drone obj,int min_battery_level,int max_weight){
            if (obj.getState()!= State.IDLE) {
                return "the state of Drone shoul be idle";
            }
            return "";
        }
    },
    WEIGHT() {
        public String validateForLoad(Drone obj,int min_battery_level,int max_weight){
            if (obj.getWeightLimit()<max_weight) {
                return "the weight of the load is to much for this Drone";
            }
            return "";
        }
    },
    BATTERY() {
        public String validateForLoad(Drone obj,int min_battery_level,int max_weight){
            if (obj.getBatteryCapacity()<min_battery_level) {
                return "the battery level should be > "+min_battery_level;
            }
            return "";
        }
    };
    public String validate(Drone obj){
        return "";
    }
    public String validateForLoad(Drone obj,int min_battery_level,int max_weight){return "";}


}
