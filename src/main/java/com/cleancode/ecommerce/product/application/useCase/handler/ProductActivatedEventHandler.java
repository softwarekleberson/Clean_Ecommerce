package com.cleancode.ecommerce.product.application.useCase.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.cleancode.ecommerce.event.ProductActivatedEvent;
import com.cleancode.ecommerce.event.infra.notification.EmailService;

public class ProductActivatedEventHandler {

	private final EmailService emailService;

	public ProductActivatedEventHandler(EmailService emailService) {
		this.emailService = emailService;
	}

	@Async
	@EventListener
	public void handle(ProductActivatedEvent event) {
		emailService.sendEmail("admin@empresa.com", "Product activated",
				"Product with ID " + event.getProductId() + " has activated success! " + "status " + event.isProductStatus());
	}
}