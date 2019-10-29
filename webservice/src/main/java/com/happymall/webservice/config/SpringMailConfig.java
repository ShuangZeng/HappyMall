package com.happymall.webservice.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration

@Qualifier
@PropertySource("classpath:mail/emailconfig.properties")
public class SpringMailConfig implements ApplicationContextAware, EnvironmentAware {

	public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

	private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";

	private static final String HOST = "mail.server.host";
	private static final String PORT = "mail.server.port";
	private static final String PROTOCOL = "mail.server.protocol";
	private static final String USERNAME = "mail.server.username";
	private static final String PASSWORD = "mail.server.password";

	private ApplicationContext applicationContext;
	private Environment environment;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	/*
	 * SPRING + JAVAMAIL: JavaMailSender instance, configured via .properties files.
	 */
	@Bean
	public JavaMailSender mailSender() throws IOException {

		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Basic mail sender configuration, based on emailconfig.properties
		mailSender.setHost(this.environment.getProperty(HOST));
		mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
		mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
		mailSender.setUsername(this.environment.getProperty(USERNAME));
		mailSender.setPassword(this.environment.getProperty(PASSWORD));

		// JavaMail-specific mail sender configuration, based on javamail.properties
		final Properties javaMailProperties = new Properties();
		javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;

	}

	/*
	 * Message externalization/internationalization for emails.
	 *
	 * NOTE we are avoiding the use of the name 'messageSource' for this bean
	 * because that would make the MessageSource defined in SpringWebConfig (and
	 * made available for the web-side template engine) delegate to this one, and
	 * thus effectively merge email messages into web messages and make both types
	 * available at the web side, which could bring undesired collisions.
	 *
	 * NOTE also that given we want this specific message source to be used by our
	 * SpringTemplateEngines for emails (and not by the web one), we will set it
	 * explicitly into each of the TemplateEngine objects with
	 * 'setTemplateEngineMessageSource(...)'
	 */
	@Bean
	public ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("mail/MailMessages");
		return messageSource;
	}

	/* ******************************************************************** */
	/* THYMELEAF-SPECIFIC ARTIFACTS FOR EMAIL */
	/* ******************************************************************** */

	@Bean
	@Primary
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

		// Resolver for HTML emails (except the editable one)
		templateEngine.addTemplateResolver(htmlTemplateResolver());

		// Message source, internationalization specific to emails
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(Integer.valueOf(2));
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

}
