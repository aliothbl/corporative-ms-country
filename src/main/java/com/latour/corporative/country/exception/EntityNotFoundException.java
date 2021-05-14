package com.latour.corporative.country.exception;

import com.latour.corporative.country.exception.handler.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:25 PM
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends HttpResponseException {
	
	public EntityNotFoundException(MessageType type, String message) {
		super(HttpStatus.NOT_FOUND, type, message);
	}
	
}
