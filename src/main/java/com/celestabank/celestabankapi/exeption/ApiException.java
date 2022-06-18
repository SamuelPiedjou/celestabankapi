package com.celestabank.celestabankapi.exeption;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
    
	public ApiException() {
		super();
		this.message = "";
		this.httpStatus = null;
		this.timeStamp = null;
	}
	
	public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public ZonedDateTime getTimeStamp() {
		return timeStamp;
	}
    
    
}
