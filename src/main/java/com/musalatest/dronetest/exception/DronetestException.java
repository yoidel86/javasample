package com.musalatest.dronetest.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class DronetestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private HttpStatus httpStatus;
    private List<ApiSubError> subErrors;
    
    public DronetestException(String message){
        this.httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        this.message=message;
    }
    public DronetestException(HttpStatus httpStatus, String message){
        this.httpStatus=httpStatus;
        this.message=message;
    }
    public DronetestException(HttpStatus httpStatus, String message, List<String> subErrors){
        this.httpStatus=httpStatus;
        this.message=message;
        this.subErrors = new ArrayList<ApiSubError>();
        for (String error: subErrors) {
            this.subErrors.add(new ApiSubError(HttpStatus.BAD_REQUEST,error));
        }
    }
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }    
    public List<ApiSubError> getSubErrors() {
        return this.subErrors;
    }

 }
