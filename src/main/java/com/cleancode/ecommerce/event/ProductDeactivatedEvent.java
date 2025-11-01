package com.cleancode.ecommerce.event;

import java.time.Instant;

public class ProductDeactivatedEvent implements DomainEvent {

	private final String productId;
	private final boolean productStatus;
	private final Instant occurredOn = Instant.now();

	public ProductDeactivatedEvent(String productId, boolean productStatus) {
		this.productId = productId;
		this.productStatus = productStatus;
	}

	public String getProductId() {
		return productId;
	}
	
	public boolean isProductStatus() {
		return productStatus;
	}

	@Override
	public Instant occurredOn() {
		return occurredOn;
	}
}