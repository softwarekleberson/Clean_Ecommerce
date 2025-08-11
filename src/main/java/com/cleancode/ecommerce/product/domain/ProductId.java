package com.cleancode.ecommerce.product.domain;

import java.util.UUID;

public class ProductId {

	private final String productId;

	public ProductId() {
		this.productId = UUID.randomUUID().toString();
	}

	public ProductId(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
}
