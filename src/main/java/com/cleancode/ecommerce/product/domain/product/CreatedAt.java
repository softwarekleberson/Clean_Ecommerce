package com.cleancode.ecommerce.product.domain.product;

import java.time.LocalDateTime;

public class CreatedAt {

	private LocalDateTime createdAt;
	
	public CreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
