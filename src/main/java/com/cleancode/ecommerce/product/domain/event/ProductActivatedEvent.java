package com.cleancode.ecommerce.product.domain.event;

import java.time.Instant;

public class ProductActivatedEvent implements DomainEvent {

	private final String productId;
	private final Instant occuredOn = Instant.now();

	public ProductActivatedEvent(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	@Override
	public Instant occurredOn() {
		return occuredOn;
	}
}