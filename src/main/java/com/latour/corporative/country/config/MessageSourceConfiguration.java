package com.latour.corporative.country.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

import static com.latour.corporative.country.api.ApiValues.Params.DEFAULT_LOCALE;

/**
 * @author Alioth Latour
 * @datetime 5/14/2021 4:31 PM
 */

@Configuration
public class MessageSourceConfiguration {
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setDefaultLocale(DEFAULT_LOCALE);
		return messageSource;
	}
	
}
