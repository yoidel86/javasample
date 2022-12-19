package com.musalatest.dronetest.validator;

import com.musalatest.dronetest.model.Drone;
import com.musalatest.dronetest.model.Load;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Validator {
    int min_battery_level;
    public Validator (@Value("${validations.battery.min}")
                      int min_battery_level){
        this.min_battery_level = min_battery_level;
    }

    public List<String> isValid(Load obj) {
        List<String> errors = new ArrayList<>();

        for (LoadValidator loadValidator : LoadValidator.values()) {
            String validationResult = loadValidator.validate(obj);
            if (!StringUtils.isBlank(validationResult)) {
                errors.add(validationResult);
            }
        }
        return errors;
    }


    public List<String> isValidForLoad(Drone obj,int max_weight){
        List<String> errors = new ArrayList<>();
        for (DroneValidator droneValidator : DroneValidator.values()) {
            String validationResult = droneValidator.validateForLoad(obj,min_battery_level,max_weight);
            if (!StringUtils.isBlank(validationResult)) {
                errors.add(validationResult);
            }
        }
        return errors;
    }
}
