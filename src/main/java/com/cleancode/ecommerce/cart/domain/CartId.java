package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class CartId {

	private final String cartId;
	
	public CartId() {
		this.cartId = UUID.randomUUID().toString();
	}
	
	public CartId(String cartId) {
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}
}
