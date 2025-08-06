package com.cleancode.ecommerce.event;

import java.time.Instant;

public class ProductActivatedEvent implements DomainEvent {

	private final String productId;
	private final boolean productStatus;
	private final Instant occuredOn = Instant.now();

	public ProductActivatedEvent(String productId, boolean productStatus) {
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
		return occuredOn;
	}
}