package com.timesheet.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	@Autowired
    private MailSender wiredMailSender;
	
	private static MailSender mailSender;
	
	@PostConstruct
	private void init() {
		mailSender = wiredMailSender;
	}
	
	public static void sendMail(String[] toAddresses, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddresses);
        message.setSubject(subject);
        message.setText(body);
		mailSender.send(message);
	}
}
