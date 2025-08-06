package com.cleancode.ecommerce.product.infra.notification;

import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService implements EmailService {

	@Override
	public void sendEmail(String to, String subject, String body) {
		System.out.println("📧 [SMTP] Send email for " + to + ": " + subject + " - " + body);
	}
}
