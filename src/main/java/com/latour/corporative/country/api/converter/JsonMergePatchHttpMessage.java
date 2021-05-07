package com.latour.corporative.country.api.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonMergePatch;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import static com.latour.corporative.country.api.ApiValues.PatchMediaType.APPLICATION_MERGE_PATCH_JSON;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 12:34 PM
 */

@Component
public class JsonMergePatchHttpMessage extends AbstractHttpMessageConverter<JsonMergePatch> {
	
	public JsonMergePatchHttpMessage() {
		super(MediaType.valueOf(APPLICATION_MERGE_PATCH_JSON));
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return JsonMergePatch.class.isAssignableFrom(clazz);
	}
	
	@Override
	protected JsonMergePatch readInternal(Class<? extends JsonMergePatch> clazz, HttpInputMessage inputMessage) throws
	                                                                                                            HttpMessageNotReadableException {
		try (JsonReader reader = Json.createReader(inputMessage.getBody())) {
			return Json.createMergePatch(reader.readValue());
		} catch (Exception e) {
			throw new HttpMessageNotReadableException(e.getMessage(), inputMessage);
		}
	}
	
	@Override
	protected void writeInternal(JsonMergePatch jsonMergePatch, HttpOutputMessage outputMessage) throws
	                                                                                             HttpMessageNotWritableException {
		
		try (JsonWriter writer = Json.createWriter(outputMessage.getBody())) {
			writer.write(jsonMergePatch.toJsonValue());
		} catch (Exception e) {
			throw new HttpMessageNotWritableException(e.getMessage(), e);
		}
	}
}
