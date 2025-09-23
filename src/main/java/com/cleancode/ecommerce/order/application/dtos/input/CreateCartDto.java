package com.cleancode.ecommerce.order.application.dtos.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateCartDto {

	@NotBlank(message = "Customer id not found")
	private String customerId;
	
	@NotBlank(message = "Product id not found")
	private String productId;
	
	@Min(1)
	private int quantity;
	
	public CreateCartDto(String customerId, String productId, int quantity) {
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}