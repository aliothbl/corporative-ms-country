package com.latour.corporative.country.api.dto.request;

import java.io.Serializable;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 10:55 AM
 */

public class CountryRequest implements Serializable {
	
	private String code;
	private String simpleName;
	private String fullName;
	private String flagImageUrl;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(final String code) {
		this.code = code;
	}
	
	public String getSimpleName() {
		return simpleName;
	}
	
	public void setSimpleName(final String simpleName) {
		this.simpleName = simpleName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}
	
	public String getFlagImageUrl() {
		return flagImageUrl;
	}
	
	public void setFlagImageUrl(final String flagImageUrl) {
		this.flagImageUrl = flagImageUrl;
	}
}
