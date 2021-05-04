package com.latour.corporative.country.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 12:38 PM
 */

public class Metadata implements Serializable {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty(value = "Pagination object")
	private Pagination pagination;
	
	public Metadata() {
	
	}
	
	public Metadata(Page<?> page) {
		this.pagination = new Pagination();
		pagination.setPageSize(page.getSize());
		pagination.setCurrentPage(page.getNumber() + 1);
		pagination.setTotalCount(page.getTotalElements());
		pagination.setTotalPages(page.getTotalPages());
		pagination.setFirst(page.isFirst());
		pagination.setLast(page.isLast());
		pagination.setNumber(page.getNumberOfElements());
	}
	
	public Pagination getPagination() {
		return pagination;
	}
}
