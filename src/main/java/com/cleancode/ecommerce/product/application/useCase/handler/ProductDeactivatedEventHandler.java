package com.cleancode.ecommerce.product.application.useCase.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.cleancode.ecommerce.product.domain.event.ProductDeactivatedEvent;
import com.cleancode.ecommerce.product.infra.notification.EmailService;

public class ProductDeactivatedEventHandler {

	private final EmailService emailService;

	public ProductDeactivatedEventHandler(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Async
	@EventListener
	public void handle(ProductDeactivatedEvent event) {
		emailService.sendEmail("admin@empresa.com", "Product deactivated",
				"Product with ID " + event.getProductId() + " has deactivated success!");
	}
}