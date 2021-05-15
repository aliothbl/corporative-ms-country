package com.latour.corporative.country.api;

import com.latour.corporative.country.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 12:35 PM
 */

public final class ApiValues {
	
	private ApiValues() {
	}
	
	public static class Headers {
		private Headers() {
		}
		
		public static final String ACCEPT_LANGUAGE = "Accept-Language";
		public static final String CONTENT_LANGUAGE = "Content-Language";
		
	}
	
	public static class Params {
		
		private Params() {
		}
		
		public static final Locale DEFAULT_LOCALE = Locale.US;
		public static final Locale[] ALLOWED_LOCALES = { Locale.US, LocaleUtil.of("pt","BR") , LocaleUtil.of("es","ES")};
		
	}
	
	public static class PatchMediaType {
		
		private PatchMediaType() {
		}
		
		public static final String APPLICATION_MERGE_PATCH_JSON = "application/merge-patch+json";
		
	}
	
}
