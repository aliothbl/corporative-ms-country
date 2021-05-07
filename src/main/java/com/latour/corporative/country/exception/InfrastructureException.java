package com.latour.corporative.country.exception;

import com.latour.corporative.country.exception.handler.HttpResponseException;
import org.springframework.http.HttpStatus;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 12:41 PM
 */

public class InfrastructureException extends HttpResponseException {
	
	public InfrastructureException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}
