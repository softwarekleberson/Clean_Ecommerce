package com.cleancode.ecommerce.shared.config.Event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.shared.domain.customer.event.EventPublisher;

@Configuration
public class EventConfig {

	@Bean
	public EventPublisher eventPublisher() {
		return new EventPublisher();
	}
}
