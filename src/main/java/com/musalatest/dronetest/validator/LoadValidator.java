package com.musalatest.dronetest.validator;

import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.Medication;

public enum LoadValidator {
    WEIGHT() {
        public String validate(Load obj){
            int total_weight = 0;
            for (Medication m: obj.getMedications()) {
                total_weight+=m.getWeight();
            }
            if (total_weight > 500 || total_weight < 0) {
                return "weight limit must be between 0 and 500!";
            }
            return "";
        }
    };
    public String validate(Load obj){
        return "";
    }

}
