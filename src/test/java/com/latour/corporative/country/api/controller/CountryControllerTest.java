package com.latour.corporative.country.api.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import util.CapturingMatcher;

import static com.latour.corporative.country.api.ApiValues.PatchMediaType.APPLICATION_MERGE_PATCH_JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 2:43 PM
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	private MockRestServiceServer mockServer;
	
	static final ThreadLocal<String> UUID = new ThreadLocal<>();
	
	@BeforeEach
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test
	@Order(1)
	void createCountryTest() throws Exception {
		
		final CapturingMatcher<String> capturingMatcher = new CapturingMatcher();
		mockMvc.perform(post("/v1/corporative/countries").contentType(APPLICATION_JSON)
		                                                     .content("{\n" +
		                                                              "    \"code\": \"+1\",\n" +
		                                                              "    \"simpleName\": \"United States\",\n" +
		                                                              "    \"fullName\": \"United States of America\",\n" +
		                                                              "    \"flagImageUrl\": \"https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg\"\n" +
		                                                              "}")).andExpect(status().isCreated()).andExpect(
				jsonPath("$.data.uuid", is(capturingMatcher))).andExpect(jsonPath("$.data.code", is("+1"))).andExpect(
				jsonPath("$.data.simpleName", is("United States"))).andExpect(
				jsonPath("$.data.fullName", is("United States of America"))).andExpect(jsonPath("$.data.flagImageUrl",
		                                                                                        is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg")));
		
		UUID.set(capturingMatcher.getLastValue());
		mockServer.verify();
		
	}
	
	@Test
	@Order(2)
	void listCountriesTest() throws Exception {
		
		mockMvc.perform(get("/v1/corporative/countries").contentType(APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.data.[*]", hasSize(1)))
		
		       .andExpect(jsonPath("$.metadata.pagination.pageSize", is(10)))
		       .andExpect(jsonPath("$.metadata.pagination.currentPage", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.totalCount", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.totalPages", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.number", is(1)))
		       .andExpect(jsonPath("$.metadata.pagination.first", is(true)))
		       .andExpect(jsonPath("$.metadata.pagination.last", is(true)))
		
		       .andExpect(jsonPath("$.data.[0].uuid", is(this.UUID.get())))
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
		mockMvc.perform(get("/v1/corporative/countries/" + this.UUID.get()).contentType(APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.data.uuid", is(UUID.get())))
		       .andExpect(jsonPath("$.data.code", is("+1")))
		       .andExpect(jsonPath("$.data.simpleName", is("United States")))
		       .andExpect(jsonPath("$.data.fullName", is("United States of America")))
		       .andExpect(jsonPath("$.data.flagImageUrl",
		                           is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4x3.svg")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(4)
	void patchCountryByUuidTest() throws Exception {
		
		mockMvc.perform(patch("/v1/corporative/countries/" + this.UUID.get()).contentType(
				MediaType.valueOf(APPLICATION_MERGE_PATCH_JSON))
		                                                                         .content(
				                                                                         "{ \"flagImageUrl\": \"https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4ec7ea92-af48.svg\" }"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.data.uuid", is(UUID.get())))
		       .andExpect(jsonPath("$.data.code", is("+1")))
		       .andExpect(jsonPath("$.data.simpleName", is("United States")))
		       .andExpect(jsonPath("$.data.fullName", is("United States of America")))
		       .andExpect(jsonPath("$.data.flagImageUrl",
		                           is("https://s3.amazonaws.com/media.latourtec.com/img/us-flag-4ec7ea92-af48.svg")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(5)
	void putCountryByUuidTest() throws Exception {
		
		mockMvc.perform(put("/v1/corporative/countries/" + this.UUID.get()).contentType(APPLICATION_JSON)
		                                                                       .content("{ " +
		                                                                                "\"code\": \"+112\"," +
		                                                                                "\"simpleName\": \"Thirteen Colonies\"," +
		                                                                                "\"fullName\": \"Thirteen British Colonies\"," +
		                                                                                "\"flagImageUrl\": \"https://s3.amazonaws.com/media.latourtec.com/img/tc-flag-4x2.svg\" " +
		                                                                                "}"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.data.uuid", is(UUID.get())))
		       .andExpect(jsonPath("$.data.code", is("+112")))
		       .andExpect(jsonPath("$.data.simpleName", is("Thirteen Colonies")))
		       .andExpect(jsonPath("$.data.fullName", is("Thirteen British Colonies")))
		       .andExpect(jsonPath("$.data.flagImageUrl",
		                           is("https://s3.amazonaws.com/media.latourtec.com/img/tc-flag-4x2.svg")));
		
		mockServer.verify();
		
	}
	
	@Test
	@Order(6)
	void getCountryEntityNotFoundDefaultLocaleTest() throws Exception {
		
		mockMvc.perform(get("/v1/corporative/countries/3ff967f6-a9fe-11eb").contentType(APPLICATION_JSON))
		       .andExpect(status().isNotFound())
		       .andExpect(jsonPath("$.status", is(404)))
		       .andExpect(jsonPath("$.timestamp", notNullValue()))
		       .andExpect(jsonPath("$.cause", is("Not Found")))
		       .andExpect(jsonPath("$.message", is("Entity not found for identifier 3ff967f6-a9fe-11eb")))
		       .andExpect(jsonPath("$.path", is("/v1/corporative/countries/3ff967f6-a9fe-11eb")));
		
		mockServer.verify();
	}
	
	@Test
	@Order(7)
	void getCountryEntityNotFoundEsESLocaleTest() throws Exception {
		
		mockMvc.perform(get("/v1/corporative/countries/3ff967f6-a9fe-11eb")
				                .header("Accept-Language", "es-ES")
		                        .contentType(APPLICATION_JSON))
		       .andExpect(status().isNotFound())
		       .andExpect(header().string("Accept-Language", "en-US, pt-BR, es-ES"))
		       .andExpect(header().string("Content-Language", "es-ES"))
		       .andExpect(jsonPath("$.status", is(404)))
		       .andExpect(jsonPath("$.timestamp", notNullValue()))
		       .andExpect(jsonPath("$.cause", is("Not Found")))
		       .andExpect(jsonPath("$.message", is("Entidad no encontrada para el identificador 3ff967f6-a9fe-11eb")))
		       .andExpect(jsonPath("$.path", is("/v1/corporative/countries/3ff967f6-a9fe-11eb")));
		
		mockServer.verify();
	}
	
	@Test
	@Order(8)
	void getCountryEntityNotFoundCkCKLocaleTest() throws Exception {
		
		mockMvc.perform(get("/v1/corporative/countries/3ff967f6-a9fe-11eb")
				                .header("Accept-Language", "ck-CK")
		                        .contentType(APPLICATION_JSON))
		       .andExpect(status().isNotFound())
		       .andExpect(header().string("Accept-Language", "en-US, pt-BR, es-ES"))
		       .andExpect(header().string("Content-Language", "en-US"))
		       .andExpect(jsonPath("$.status", is(404)))
		       .andExpect(jsonPath("$.timestamp", notNullValue()))
		       .andExpect(jsonPath("$.cause", is("Not Found")))
		       .andExpect(jsonPath("$.message", is("Entity not found for identifier 3ff967f6-a9fe-11eb")))
		       .andExpect(jsonPath("$.path", is("/v1/corporative/countries/3ff967f6-a9fe-11eb")));
		
		mockServer.verify();
	}
	
	@Test
	@Order(9)
	void deleteCountryEntityTest() throws Exception {
		
		mockMvc.perform(delete("/v1/corporative/countries/" + this.UUID.get()).contentType(APPLICATION_JSON))
		       .andExpect(status().isNoContent());
		
		mockServer.verify();
		
	}
	
}