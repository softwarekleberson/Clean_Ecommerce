package com.cleancode.ecommerce.event.infra.notification;

public interface EmailService {

	void sendEmail(String to, String subject, String body);
}
