package com.cleancode.ecommerce.event;

public interface EventPublisher {

	void publish (DomainEvent event);
}
