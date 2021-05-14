package com.latour.corporative.country.exception;

/**
 * @author Alioth Latour
 * @datetime 5/14/2021 4:39 PM
 */

public enum MessageType {
	
	ENTITY_NOT_FOUND("error.entity.not-found");
	
	MessageType(String code) {
		this.code = code;
		
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
}
