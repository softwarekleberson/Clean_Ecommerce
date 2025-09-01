package com.cleancode.ecommerce.order.domain.cart;

import java.util.UUID;

public class CartItemId {

	private final String cartItemId;
	
	public CartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	public CartItemId() {
		this.cartItemId = UUID.randomUUID().toString();
	}
	
	public String getCartItemId() {
		return cartItemId;
	}
}
