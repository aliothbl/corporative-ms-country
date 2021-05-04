package com.latour.corporative.country.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latour.corporative.country.api.dto.WrapperResponse;
import com.latour.corporative.country.api.dto.response.CountryResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 2:43 PM
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	private MockRestServiceServer mockServer;
	
	private static String uuid;
	
	@BeforeEach
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test
	@Order(1)
	void createCountryTest() throws Exception {
		
		ResultActions result = mockMvc.perform(post("/api/v1/corporative/countries")
				                                       .contentType(APPLICATION_JSON)
				                                       .content("{\n" +
				                                                "    \"code\": \"+1\",\n" +
				                                                "    \"simpleName\": \"United States\",\n" +
				                                                "    \"fullName\": \"United States of America\",\n" +
				                                                "    \"flagImageUrl\": \"https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg\"\n" +
				                                                "}"))
		                              .andExpect(status().isOk())
		                              .andExpect(jsonPath("$.data.uuid", notNullValue()))
		                              .andExpect(jsonPath("$.data.code", is("+1")))
		                              .andExpect(jsonPath("$.data.simpleName", is("United States")))
		                              .andExpect(jsonPath("$.data.fullName", is("United States of America")))
		                              .andExpect(jsonPath("$.data.flagImageUrl",
		                                                  is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg")));
		
		String contentAsString = result.andReturn().getResponse().getContentAsString();
		WrapperResponse<CountryResponse> response = objectMapper.readValue(
				contentAsString, new TypeReference<WrapperResponse<CountryResponse>>() {
				});
		
		this.uuid = response.getData().getUuid();
		mockServer.verify();
		
	}
	
	@Test
	@Order(2)
	void listCountriesTest() throws Exception {
		
		mockMvc.perform(get("/api/v1/corporative/countries").contentType(APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.data.[*]", hasSize(1)))
		
		       .andExpect(jsonPath("$.metadata.pagination.pageSize", is(10)))
		       .andExpect(jsonPath("$.metadata.pagination.currentPage", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.totalCount", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.totalPages", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.number", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.first", is(true)))
		       .andExpect(jsonPath("$.metadata.pagination.last", is(true)))
		
		       .andExpect(jsonPath("$.data.[0].uuid", is(this.uuid)))
		       .andExpect(jsonPath("$.data.[0].code", is("+1")))
		       .andExpect(jsonPath("$.data.[0].simpleName", is("United States")))
		       .andExpect(jsonPath("$.data.[0].fullName", is("United States of America")))
		       .andExpect(jsonPath("$.data.[0].flagImageUrl",
		                           is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(3)
	void getCountryByUuidTest() throws Exception {
		
		mockMvc.perform(get("/api/v1/corporative/countries/" + this.uuid).contentType(APPLICATION_JSON))
		       .andExpect(status().isOk())
		
		       .andExpect(jsonPath("$.data.uuid", is(uuid)))
		       .andExpect(jsonPath("$.data.code", is("+1")))
		       .andExpect(jsonPath("$.data.simpleName", is("United States")))
		       .andExpect(jsonPath("$.data.fullName", is("United States of America")))
		       .andExpect(jsonPath("$.data.flagImageUrl",
		                           is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(4)
	void getCountryEntityNotFoundTest() throws Exception {
		
		mockMvc.perform(get("/api/v1/corporative/countries/3ff967f6-a9fe-11eb").contentType(APPLICATION_JSON))
		       .andExpect(status().isNotFound())
		       .andExpect(jsonPath("$.status", is(404)))
		       .andExpect(jsonPath("$.timestamp", notNullValue()))
		       .andExpect(jsonPath("$.error", is("Not Found")))
		       .andExpect(jsonPath("$.path", is("/api/v1/corporative/countries/3ff967f6-a9fe-11eb")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(5)
	void deleteCountryEntityTest() throws Exception {
		
		mockMvc.perform(delete("/api/v1/corporative/countries/" + this.uuid).contentType(APPLICATION_JSON))
		       .andExpect(status().isNoContent());
		
		mockServer.verify();
		
	}
	
}