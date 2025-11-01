package com.cleancode.ecommerce.product.application.useCase.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.cleancode.ecommerce.event.NewProductEvent;
import com.cleancode.ecommerce.event.infra.notification.EmailService;

public class NewProductEventHandler {

	private final EmailService emailService;

	public NewProductEventHandler(EmailService emailService) {
		this.emailService = emailService;
	}
		
	@Async
	@EventListener
	public void handle(NewProductEvent event) {
		emailService.sendEmail("admin@empresa.com", "New product",
		String.format
		("Product ID: %s, Name: %s, Category: %s",
		event.getProductId(), event.getName(), event.getCategory()));
	}
}