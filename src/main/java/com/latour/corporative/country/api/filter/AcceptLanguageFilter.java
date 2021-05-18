package com.latour.corporative.country.api.filter;

import com.latour.corporative.country.util.LocaleUtil;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.latour.corporative.country.api.ApiValues.Headers.ACCEPT_LANGUAGE;
import static com.latour.corporative.country.api.ApiValues.Headers.CONTENT_LANGUAGE;

/**
 * @author Alioth Latour
 * @datetime 5/15/2021 5:48 AM
 */

@WebFilter("/v1/corporative/countries/*")
public class AcceptLanguageFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	                                                                                                 ServletException {
		final StringBuilder acceptLanguage = new StringBuilder(Locale.US.toLanguageTag());
		acceptLanguage.append(",");
		acceptLanguage.append(",");
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader(ACCEPT_LANGUAGE, LocaleUtil.getAllowedLanguagesTags());
		httpServletResponse.setHeader(CONTENT_LANGUAGE, getLanguage());
		
		chain.doFilter(request, response);
	}
	
	private String getLanguage() {
		String language = LocaleContextHolder.getLocale().toLanguageTag();
		if (LocaleUtil.getAllowedLanguagesTags().toLowerCase().indexOf(language.toLowerCase()) != -1) {
			return language;
		}
		
		return Locale.US.toLanguageTag();
	}
	
}
