package com.cleancode.ecommerce.event;

import java.time.Instant;

public class NewProductEvent implements DomainEvent {

	private final String productId;
	private final String name;
	private final String category;
	private final Instant occuredOn = Instant.now();
	
	public NewProductEvent(String productId, String name, String category) {
		this.productId = productId;
		this.name = name;
		this.category = category;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public Instant occurredOn() {
		return occuredOn;
	}
}