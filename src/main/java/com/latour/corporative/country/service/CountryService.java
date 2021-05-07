package com.latour.corporative.country.service;

import com.latour.corporative.country.api.dto.Metadata;
import com.latour.corporative.country.api.dto.WrapperListResponse;
import com.latour.corporative.country.api.dto.WrapperResponse;
import com.latour.corporative.country.api.dto.filter.PageFilter;
import com.latour.corporative.country.api.dto.request.CountryRequest;
import com.latour.corporative.country.api.dto.response.CountryResponse;
import com.latour.corporative.country.exception.EntityNotFoundException;
import com.latour.corporative.country.mapper.CountryMapper;
import com.latour.corporative.country.model.Country;
import com.latour.corporative.country.repository.CountryRepository;
import com.latour.corporative.country.util.ObjectMapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.json.JsonMergePatch;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 11:58 AM
 */

@Service
public class CountryService {
	
	private final CountryRepository repository;
	private final CountryMapper mapper;
	
	public CountryService(final CountryRepository repository, final CountryMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public WrapperListResponse<CountryResponse> listAll(final PageFilter filter) {
		
		Pageable paging = PageRequest.of(filter.getPage() - 1, filter.getPageSize());
		Page<Country> countryPage = repository.findAll(paging);
		List<CountryResponse> data = countryPage.get().map(mapper::from).collect(Collectors.toList());
		
		return WrapperListResponse.of(data, new Metadata(countryPage));
	}
	
	public WrapperResponse<CountryResponse> getBy(final String uuid) {
		return WrapperResponse.of(mapper.from(getCountry(uuid)));
	}
	
	public WrapperResponse<CountryResponse> merge(final String uuid, final JsonMergePatch jsonMergePatch) {
		Country country = ObjectMapperUtil.mergePatch(jsonMergePatch, getCountry(uuid), Country.class);
		/* Keeping the same uuid in the document */
		country.setUuid(uuid);
		repository.save(country);
		return WrapperResponse.of(mapper.from(repository.save(country)));
	}
	
	public void deleteBy(final String uuid) {
		repository.delete(getCountry(uuid));
	}
	
	private Country getCountry(final String uuid) {
		return repository.findBy(uuid).orElseThrow(
				() -> new EntityNotFoundException("Entity not found for identifier '" + uuid + "'"));
	}
	
	public WrapperResponse<CountryResponse> create(final CountryRequest request) {
		final Country document = mapper.from(request);
		final Country country = repository.insert(document);
		return WrapperResponse.of(mapper.from(country));
	}
	
}
