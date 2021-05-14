package com.latour.corporative.country.api;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 12:35 PM
 */

public final class ApiValues {
	
	private ApiValues() {
	}
	
	public static class Params {
		
		private Params() {
		}
		
		public static final String LOCALE = "locale";
		
		public static final String[] ALLOWED_LOCALES = { "en-US", "pt-BR", "es-ES" };
		
	}
	
	public static class PatchMediaType {
		
		private PatchMediaType() {
		}
		
		public static final String APPLICATION_MERGE_PATCH_JSON = "application/merge-patch+json";
		
	}
	
}
