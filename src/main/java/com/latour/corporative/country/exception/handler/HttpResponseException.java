package com.latour.corporative.country.exception.handler;

import com.latour.corporative.country.exception.MessageType;
import com.latour.corporative.country.util.MessagesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:26 PM
 */

public class HttpResponseException extends RuntimeException implements ClientHttpResponse {
	
	private final HttpStatus httpStatus;
	
	public HttpResponseException(HttpStatus httpStatus, MessageType type, String message) {
		super(MessagesUtil.getMessage(type.getCode(), message));
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	@Override
	public HttpStatus getStatusCode() throws IOException {
		return httpStatus;
	}
	
	@Override
	public int getRawStatusCode() throws IOException {
		return 0;
	}
	
	@Override
	public String getStatusText() throws IOException {
		return null;
	}
	
	@Override
	public void close() {
	
	}
	
	@Override
	public InputStream getBody() throws IOException {
		return null;
	}
	
	@Override
	public HttpHeaders getHeaders() {
		return null;
	}
}
