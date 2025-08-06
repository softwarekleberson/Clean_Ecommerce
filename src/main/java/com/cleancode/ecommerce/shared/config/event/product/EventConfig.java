package com.cleancode.ecommerce.shared.config.event.product;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.product.application.useCase.handler.ProductActivatedEventHandler;
import com.cleancode.ecommerce.product.application.useCase.handler.ProductDeactivatedEventHandler;
import com.cleancode.ecommerce.product.infra.notification.EmailService;
import com.cleancode.ecommerce.product.infra.notification.SpringEventPublisher;
import com.cleancode.ecommerce.shared.event.product.event.EventPublisher;

@Configuration
public class EventConfig {

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
		return new ProductDeactivatedEventHandler (emailService);
	}
}