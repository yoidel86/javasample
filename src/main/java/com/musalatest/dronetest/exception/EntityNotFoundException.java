package com.musalatest.dronetest.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Map;


public class EntityNotFoundException extends DronetestException {

    public EntityNotFoundException(Class clazz, Map<String, Object> searchParams) {
        super(HttpStatus.NOT_FOUND,StringUtils.capitalize(clazz.getSimpleName()) +
                " was not found for parameters " +
                searchParams);
    }

}
