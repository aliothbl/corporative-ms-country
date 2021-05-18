package com.latour.corporative.country.api.controller;

import com.latour.corporative.country.api.dto.WrapperListResponse;
import com.latour.corporative.country.api.dto.WrapperResponse;
import com.latour.corporative.country.api.dto.filter.PageFilter;
import com.latour.corporative.country.api.dto.request.CountryRequest;
import com.latour.corporative.country.api.dto.response.CountryResponse;
import com.latour.corporative.country.exception.EntityNotFoundException;
import com.latour.corporative.country.service.CountryService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.json.JsonMergePatch;
import java.util.Locale;

import static com.latour.corporative.country.api.ApiValues.PatchMediaType.APPLICATION_MERGE_PATCH_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:42 PM
 */

@RestController
@RequestMapping("/v1/corporative/countries")
@Api(tags = { "Corporative Country" })
public class CountryController {
	
	private final CountryService service;
	
	public CountryController(final CountryService service) {
		this.service = service;
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@ApiOperation(value = "Create a country", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Country created", response = CountryResponse.class) })
	@PostMapping
	public ResponseEntity<WrapperResponse<CountryResponse>> createCountry(final @RequestBody CountryRequest request,
	                                                                      @ApiIgnore Locale locale) {
		return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@ApiOperation(value = "Find a country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country found", response = CountryResponse.class),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@GetMapping("/{uuid}")
	public ResponseEntity<WrapperResponse<CountryResponse>> getCountry(final @PathVariable String uuid,
	                                                                   @ApiIgnore Locale locale) {
		return ResponseEntity.ok(service.getBy(uuid));
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@ApiOperation(value = "Merge a Country data",
	              consumes = APPLICATION_MERGE_PATCH_JSON,
	              produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country merged", response = CountryResponse.class),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@PatchMapping("/{uuid}")
	public ResponseEntity<WrapperResponse<CountryResponse>> mergeCountry(final @PathVariable String uuid,
	                                                                     final @RequestBody JsonMergePatch jsonMergePatch,
	                                                                     @ApiIgnore Locale locale) {
		return ResponseEntity.ok(service.merge(uuid, jsonMergePatch));
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@PutMapping("/{uuid}")
	public ResponseEntity<WrapperResponse<CountryResponse>> updateCountry(final @PathVariable String uuid,
	                                                                      final @RequestBody CountryRequest request,
	                                                                      @ApiIgnore Locale locale) {
		return ResponseEntity.ok(service.update(uuid, request));
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@ApiOperation(value = "List all countries", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200,
	                                     message = "Countries listed",
	                                     response = CountryResponse[].class) })
	@GetMapping()
	public ResponseEntity<WrapperListResponse<CountryResponse>> listCountries(final PageFilter filter,
	                                                                          @ApiIgnore Locale locale) {
		return ResponseEntity.ok(service.listAll(filter));
	}
	
	@ApiImplicitParam(name = "Accept-Language",
	                  value = "en-US",
	                  paramType = "header",
	                  dataTypeClass = String.class,
	                  example = "en-US")
	@ApiOperation(value = "Delete a country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Country deleted"),
	                        @ApiResponse(code = 404,
	                                     message = "Country not found",
	                                     response = EntityNotFoundException.class) })
	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCountry(final @PathVariable String uuid,
	                          @ApiIgnore Locale locale) {
		service.deleteBy(uuid);
	}
	
}
