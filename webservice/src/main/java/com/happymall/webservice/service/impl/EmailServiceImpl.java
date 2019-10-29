/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.happymall.webservice.service.impl;

import java.io.File;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private static final String EMAIL_BUYER_PURCHASE_TEMPLATE_NAME = "html/buyer-purchase";

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	public void notifyBuyerOfPurchase(Orders orders) throws MessagingException {

		String imageFooterName = "/home/biji/github/latest/HappyMall/webservice/src/main/resources/mail/html/images/logo5.png";
		String imageHeaderName = "/home/biji/github/latest/HappyMall/webservice/src/main/resources/mail/html/images/logo1.png";

		// Prepare the evaluation context
		Locale locale = new Locale("en");
		final Context ctx = new Context(locale);
		ctx.setVariable("name", orders.getUser().getFullName());
		ctx.setVariable("orders", orders);
		ctx.setVariable("imageResourceName", imageHeaderName); // so that we can reference it from HTML
		ctx.setVariable("imageFooterName", imageFooterName);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
		message.setSubject("Happy Mall Purchase");
		message.setFrom("purchases@happymall.com");
		message.setTo(orders.getUser().getEmail());

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.htmlTemplateEngine.process(EMAIL_BUYER_PURCHASE_TEMPLATE_NAME, ctx);
		message.setText(htmlContent, true /* isHtml */);

		// Add the inline image, referenced from the HTML code as
		// "cid:${imageResourceName}"
		message.addInline(imageHeaderName, new File(
				"/home/biji/github/latest/HappyMall/webservice/src/main/resources/mail/html/images/logo1.png"));
		message.addInline(imageFooterName, new File(
				"/home/biji/github/latest/HappyMall/webservice/src/main/resources/mail/html/images/logo5.png"));

		// Send mail
		this.mailSender.send(mimeMessage);
	}

}
