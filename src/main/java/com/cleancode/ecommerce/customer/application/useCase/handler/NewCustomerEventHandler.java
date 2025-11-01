package com.cleancode.ecommerce.customer.application.useCase.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.cleancode.ecommerce.event.NewCustomerEvent;
import com.cleancode.ecommerce.event.infra.notification.EmailService;

public class NewCustomerEventHandler {

	private final EmailService emailService;

	public NewCustomerEventHandler(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Async
	@EventListener
	public void handler(NewCustomerEvent event) {
		emailService.sendEmail(
		"admin@empresa.com",
		"Welcome To Hatchards",
		String.format("Name: %s, Email: %s ", event.getName(), event.getEmail()));
	}
}