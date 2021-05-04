package com.latour.corporative.country.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 12:37 PM
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WrapperResponse<T> implements Serializable {
	
	@ApiModelProperty(value = "Adicional information for result", required = true)
	private final Metadata metadata;
	
	@ApiModelProperty(value = "Data", required = true)
	private T data;
	
	public WrapperResponse() {
		this.metadata = new Metadata();
	}
	
	public static <T> WrapperResponse<T> of(T data) {
		final WrapperResponse<T> response = new WrapperResponse<>();
		response.setData(data);
		return response;
	}
	
	public void setData(final T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
}
