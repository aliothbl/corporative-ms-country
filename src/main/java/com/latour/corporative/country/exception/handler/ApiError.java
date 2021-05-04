package com.latour.corporative.country.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 9:48 PM
 */

public class ApiError implements Serializable {
	
	@JsonIgnore
	private HttpStatus httpStatus;
	private Integer status;
	private Date timestamp;
	private String message;
	private String error;
	private String path;
	
	public ApiError(HttpStatus httpStatus, String message, String path) {
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = message;
		this.timestamp = new Date();
		this.path = path;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getError() {
		return error;
	}
	
	public String getPath() {
		return path;
	}
}
