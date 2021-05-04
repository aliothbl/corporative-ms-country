package com.latour.corporative.country.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 9:47 PM
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandler {
	
	private final HttpServletRequest request;
	
	private ExceptionHandler(HttpServletRequest request) {
		this.request = request;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ HttpResponseException.class })
	public ResponseEntity<Object> handleEmptyResultsException(HttpResponseException ex) {
		return new ResponseEntity<>(new ApiError(ex.getHttpStatus(), ex.getMessage(), this.request.getRequestURI()),
		                            ex.getHttpStatus());
	}
	
}
