package com.latour.corporative.country.mapper;

import com.latour.corporative.country.api.dto.request.CountryRequest;
import com.latour.corporative.country.api.dto.response.CountryResponse;
import com.latour.corporative.country.model.Country;
import org.mapstruct.Mapper;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:52 PM
 */

@Mapper(componentModel = "spring")
public interface CountryMapper {
	
	Country from(CountryRequest request);
	
	CountryResponse from(Country request);

}
