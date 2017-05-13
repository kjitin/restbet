package com.skybet.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class BetException extends RuntimeException {

	private static final long serialVersionUID = 694719516556879766L;
	
	
	public BetException(String message){
		super(message);
	}


}