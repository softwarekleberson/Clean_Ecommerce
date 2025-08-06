package com.cleancode.ecommerce.product.infra.notification;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.cleancode.ecommerce.product.domain.event.DomainEvent;
import com.cleancode.ecommerce.shared.event.product.event.EventPublisher;

@Component
public class SpringEventPublisher implements EventPublisher {

	private final ApplicationEventPublisher publisher;

	public SpringEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publish(DomainEvent event) {
		publisher.publishEvent(event);
	}
}