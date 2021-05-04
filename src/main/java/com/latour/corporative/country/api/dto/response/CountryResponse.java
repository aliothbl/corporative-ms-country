package com.latour.corporative.country.api.dto.response;

import com.latour.corporative.country.api.dto.request.CountryRequest;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:45 PM
 */

public class CountryResponse extends CountryRequest {
	
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
}
