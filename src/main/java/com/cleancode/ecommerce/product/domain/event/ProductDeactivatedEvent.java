package com.cleancode.ecommerce.product.domain.event;

import java.time.Instant;

public class ProductDeactivatedEvent implements DomainEvent {

	private final String productId;
	private final Instant occurredOn = Instant.now();

	public ProductDeactivatedEvent(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	@Override
	public Instant occurredOn() {
		return occurredOn;
	}
}