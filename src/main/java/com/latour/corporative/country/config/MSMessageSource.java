package com.latour.corporative.country.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;

/**
 * @author Alioth Latour
 * @datetime 5/14/2021 7:28 PM
 */

public class MSMessageSource implements MessageSourceAware {
	
	private MessageSource messageSource;
	
	@Bean
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
