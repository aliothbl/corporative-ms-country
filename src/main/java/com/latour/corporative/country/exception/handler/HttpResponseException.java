package com.latour.corporative.country.exception.handler;

import org.springframework.http.HttpStatus;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:26 PM
 */

public class HttpResponseException extends RuntimeException {
	
	private final HttpStatus httpStatus;
	private final String message;
	
	public HttpResponseException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
