package com.latour.corporative.country.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.latour.corporative.country.api.ApiValues.Params.ALLOWED_LOCALES;

/**
 * @author Alioth Latour
 * @datetime 5/15/2021 6:25 AM
 */

public class LocaleUtil {
	
	public static final String LANGUAGE_TAG_DELIMITER = ", ";
	
	private LocaleUtil(){}
	
	public static Locale of(final String lang, final String country) {
		return new Locale(lang, country);
	}
	
	public static String getAllowedLanguagesTags(){
		return Arrays.stream(ALLOWED_LOCALES).map( locale -> locale.toLanguageTag())
		             .collect(Collectors.joining(LANGUAGE_TAG_DELIMITER));
	}
	
}
