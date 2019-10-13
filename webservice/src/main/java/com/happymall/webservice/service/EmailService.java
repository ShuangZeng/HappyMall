package com.happymall.webservice.service;

import java.util.Locale;

import javax.mail.MessagingException;


public interface EmailService {
	
	public void sendMailWithInline(final String recipientName, final String recipientEmail, final String imageResourceName,
	        final byte[] imageBytes, final String imageContentType, final Locale locale) throws MessagingException;

}
