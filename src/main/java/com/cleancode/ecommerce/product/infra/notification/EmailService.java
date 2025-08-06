package com.cleancode.ecommerce.product.infra.notification;

public interface EmailService {

	void sendEmail(String to, String subject, String body);
}
