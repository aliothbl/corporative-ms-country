package com.latour.corporative.country.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

import javax.json.JsonMergePatch;
import javax.json.JsonValue;
import java.util.TimeZone;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 12:41 PM
 */

public class ObjectMapperUtil {
	
	public static final ObjectMapper MAPPER = getMapper();
	
	private ObjectMapperUtil() {
	}
	
	public static <T> T mergePatch(JsonMergePatch mergePatch, T targetBean, Class<T> beanClass) {
		JsonValue target = MAPPER.convertValue(targetBean, JsonValue.class);
		JsonValue patched = mergePatch.apply(target);
		return MAPPER.convertValue(patched, beanClass);
	}
	
	private static ObjectMapper getMapper() {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).configure(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.setTimeZone(TimeZone.getDefault());
		mapper.registerModule(new JSR353Module());
		
		return mapper;
	}
	
}

