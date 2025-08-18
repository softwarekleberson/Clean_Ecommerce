package com.cleancode.ecommerce.cart.domain;

public class CartId {

	private final String cartId;
	
	public CartId(String cartId) {
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}
}
