package com.cleancode.ecommerce.product.domain;

import java.time.LocalDateTime;

public class CreatedAt {

	private final LocalDateTime createdAt;
	
	public CreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}