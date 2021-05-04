package com.latour.corporative.country.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 4:50 PM
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WrapperListResponse<T> implements Serializable {
	
	@ApiModelProperty(value = "Adicional information for result")
	private Metadata metadata;
	
	@ApiModelProperty(value = "Lista of data")
	private List<T> data;
	
	public WrapperListResponse(List<T> data, Metadata metadata) {
		this.data = data;
		this.metadata = metadata;
	}
	
	public static <T> WrapperListResponse<T> of(List<T> data, Metadata metadata) {
		return new WrapperListResponse(data, metadata);
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public List<T> getData() {
		return data;
	}
}
