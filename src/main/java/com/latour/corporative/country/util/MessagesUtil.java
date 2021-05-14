package com.latour.corporative.country.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Alioth Latour
 * @datetime 5/14/2021 4:33 PM
 */

@Component
public class MessagesUtil {
	
	private static MessageSource messageSource;
	
	@Autowired
	public MessagesUtil(MessageSource messageSource) {
		MessagesUtil.messageSource = messageSource;
	}
	
	public static String getMessage(String messageCode, Object... args) {
		return MessageFormat.format(messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale()), args);
	}
	
}
