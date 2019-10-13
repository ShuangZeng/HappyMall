package com.happymall.webservice.notifications;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.happymall.webservice.service.EmailService;
import com.happymall.webservice.service.impl.EmailServiceImpl;

@Component
public class MessageSender {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailService emailService;
	
	@EventListener
	public void run(ContextRefreshedEvent event) {
//		try {
//			sendEmailWithAttachment();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		String recipientName = "Buba Bojang";
		String recipientEmail = "biji.application@gmail.com";
		String imgName = "HappyMall logo";
		byte[] byt = {};
		Locale locale = new Locale("en");
		
		try {
		this.emailService.sendMailWithInline(
	            recipientName, recipientEmail, imgName,
	            byt, "image/png", locale);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void send() {
		
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("biji.application@gmail.com","buba.bbojang@gmail.com");

        msg.setSubject("Testing from Happy Mall");
        msg.setText("Hello World \n This is a test message from Biji");

        javaMailSender.send(msg);
	}
	
	public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        String [] to = {"biji.application@gmail.com","buba.bbojang@gmail.com"};
        helper.setTo(to);
    
        helper.setSubject("Testing from Happy Mall");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for file!</h1>", true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("seed.csv", new ClassPathResource("seed.csv"));

        javaMailSender.send(msg);

    }

}
