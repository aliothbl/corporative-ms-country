package com.latour.corporative.country.api.dto.filter;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 5:21 PM
 */

public class PageFilter implements Serializable {
	
	@ApiModelProperty(value = "Requested page number", example = "1")
	private int page;
	
	@ApiModelProperty(value = "Requested page size", example = "10")
	private int pageSize;
	
	public PageFilter() {
		this.page = 1;
		this.pageSize = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(final int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}
}
