package com.latour.corporative.country.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 12:58 PM
 */

@Document("countries")
public class Country {
	
	@Id
	@BsonProperty(value = "_id")
	private String uuid;
	private String code;
	private String simpleName;
	private String fullName;
	private String flagImageUrl;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(final String code) {
		this.code = code;
	}
	
	public String getSimpleName() {
		return simpleName;
	}
	
	public void setSimpleName(final String simpleName) {
		this.simpleName = simpleName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}
	
	public String getFlagImageUrl() {
		return flagImageUrl;
	}
	
	public void setFlagImageUrl(final String flagImageUrl) {
		this.flagImageUrl = flagImageUrl;
	}
}
