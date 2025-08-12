package com.cleancode.ecommerce.cart.domain;

import java.util.UUID;

public class CartId {

	private final String cartId;
	
	public CartId(String cartId) {
		this.cartId = cartId;
	}
	
	public CartId() {
		this.cartId = UUID.randomUUID().toString();
	}
	
	public String getCartId() {
		return cartId;
	}
}
