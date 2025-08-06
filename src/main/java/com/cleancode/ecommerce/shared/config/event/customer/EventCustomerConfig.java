package com.cleancode.ecommerce.shared.config.event.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.application.useCase.handler.NewCustomerEventHandler;
import com.cleancode.ecommerce.product.infra.notification.EmailService;

@Configuration
public class EventCustomerConfig {

	@Bean
	public NewCustomerEventHandler newCustomerEventHandler (EmailService emailService) {
		return new NewCustomerEventHandler(emailService);
	}
}