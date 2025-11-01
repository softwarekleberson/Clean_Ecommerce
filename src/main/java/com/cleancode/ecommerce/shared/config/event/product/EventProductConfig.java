package com.cleancode.ecommerce.shared.config.event.product;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.infra.notification.EmailService;
import com.cleancode.ecommerce.event.infra.notification.SpringEventPublisher;
import com.cleancode.ecommerce.product.application.useCase.handler.NewProductEventHandler;
import com.cleancode.ecommerce.product.application.useCase.handler.ProductActivatedEventHandler;
import com.cleancode.ecommerce.product.application.useCase.handler.ProductDeactivatedEventHandler;

@Configuration
public class EventProductConfig {

	@Bean
	public EventPublisher eventPublisher(ApplicationEventPublisher publisher) {
		return new SpringEventPublisher(publisher);
	}
	
	@Bean 
	public ProductActivatedEventHandler productActivate (EmailService emailService) {
		return new ProductActivatedEventHandler(emailService);
	}
	
	@Bean
	public ProductDeactivatedEventHandler productDeactivated (EmailService emailService) {
		return new ProductDeactivatedEventHandler(emailService);
	}
	
	@Bean
	public NewProductEventHandler newProductEventHandler (EmailService emailService) {
		return new NewProductEventHandler(emailService);
	}
}