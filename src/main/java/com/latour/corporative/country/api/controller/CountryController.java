package com.latour.corporative.country.api.controller;

import com.latour.corporative.country.api.dto.WrapperListResponse;
import com.latour.corporative.country.api.dto.WrapperResponse;
import com.latour.corporative.country.api.dto.filter.PageFilter;
import com.latour.corporative.country.api.dto.request.CountryRequest;
import com.latour.corporative.country.api.dto.response.CountryResponse;
import com.latour.corporative.country.exception.EntityNotFoundException;
import com.latour.corporative.country.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;

import static com.latour.corporative.country.api.ApiValues.PatchMediaType.APPLICATION_MERGE_PATCH_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:42 PM
 */

@RestController
@RequestMapping("/api/v1/corporative/countries")
@Api(tags = { "Country" })
public class CountryController {
	
	private final CountryService service;
	
	public CountryController(final CountryService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Create a country", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country created", response = CountryResponse.class) })
	@PostMapping
	public ResponseEntity<WrapperResponse<CountryResponse>> createCountry(final @RequestBody CountryRequest request) {
		return ResponseEntity.ok(service.create(request));
	}
	
	@ApiOperation(value = "Find a country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country found", response = CountryResponse.class),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@GetMapping("/{uuid}")
	public ResponseEntity<WrapperResponse<CountryResponse>> getCountry(final @PathVariable String uuid) {
		return ResponseEntity.ok(service.getBy(uuid));
	}
	
	@ApiOperation(value = "Merge a country data",
	              consumes = APPLICATION_MERGE_PATCH_JSON,
	              produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country found", response = CountryResponse.class),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@PatchMapping("/{uuid}")
	public ResponseEntity<WrapperResponse<CountryResponse>> mergeCountry(final @PathVariable String uuid,
	                                                                     final @RequestBody
			                                                                     JsonMergePatch jsonMergePatch) {
		return ResponseEntity.ok(service.merge(uuid, jsonMergePatch));
	}
	
	@ApiOperation(value = "List all countries", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200,
	                                     message = "Countries listed",
	                                     response = CountryResponse[].class) })
	@GetMapping()
	public ResponseEntity<WrapperListResponse<CountryResponse>> listCountries(final PageFilter filter) {
		return ResponseEntity.ok(service.listAll(filter));
	}
	
	@ApiOperation(value = "Delete a country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Country deleted"),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCountry(final @PathVariable String uuid) {
		service.deleteBy(uuid);
	}
	
}
