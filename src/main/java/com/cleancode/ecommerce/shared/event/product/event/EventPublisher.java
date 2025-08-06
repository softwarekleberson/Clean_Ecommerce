package com.cleancode.ecommerce.shared.event.product.event;

import com.cleancode.ecommerce.product.domain.event.DomainEvent;

public interface EventPublisher {

	void publish (DomainEvent event);
}
