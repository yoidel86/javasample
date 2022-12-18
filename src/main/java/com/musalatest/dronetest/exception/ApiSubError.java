package com.musalatest.dronetest.exception;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName("suberror")
public class ApiSubError {

	public ApiSubError() {
	}
	
	public ApiSubError(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	private HttpStatus status;
	private String message;

}
