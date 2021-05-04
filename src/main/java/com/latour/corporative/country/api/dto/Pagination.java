package com.latour.corporative.country.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 12:23 PM
 */

public class Pagination implements Serializable {
	
	@ApiModelProperty(value = "Page size", example = "10")
	private int pageSize;
	
	@ApiModelProperty(value = "Current page", example = "1")
	private int currentPage;
	
	@ApiModelProperty(value = "Total of elements on all pages", example = "123")
	private long totalCount;
	
	@ApiModelProperty(value = "Total of pages", example = "10")
	private int totalPages;
	
	@ApiModelProperty(value = "Total of pages elements", example = "10")
	private int number;
	
	@ApiModelProperty(value = "Flag indicating is the first page", example = "true")
	private boolean first;
	
	@ApiModelProperty(value = "Flag indicating is the last page", example = "false")
	private boolean last;
	
	public Pagination() {
		this.pageSize = 10;
		this.currentPage = 1;
		this.totalCount = 0;
		this.totalPages = 0;
		this.number = 1;
		this.first = true;
		this.last = false;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(final int number) {
		this.number = number;
	}
	
	public boolean isFirst() {
		return first;
	}
	
	public void setFirst(final boolean first) {
		this.first = first;
	}
	
	public boolean isLast() {
		return last;
	}
	
	public void setLast(final boolean last) {
		this.last = last;
	}
}
