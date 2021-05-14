package com.latour.corporative.country.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;

import static com.latour.corporative.country.api.ApiValues.Params.ALLOWED_LOCALES;
import static com.latour.corporative.country.api.ApiValues.Params.LOCALE;

/**
 * @author Alioth Latour
 * @datetime 5/14/2021 6:08 PM
 */

@Configuration
public class MSLocaleResolver extends CookieLocaleResolver {
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		
		final Enumeration<String> parameters = request.getParameterNames();
		while (parameters.hasMoreElements()) {
			final String name = parameters.nextElement();
			if (LOCALE.equals(name)) {
				final String value = request.getParameter(LOCALE);
				if (Arrays.stream(ALLOWED_LOCALES).anyMatch(s -> s.equalsIgnoreCase(value))) {
					String[] split = value.split("-");
					return new Locale(split[0], split[1]);
				}
			}
		}
		
		return Locale.US;
	}
}


