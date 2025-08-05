package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class IdCart {

	private final String idCart;
	
	public IdCart() {
		this.idCart = UUID.randomUUID().toString();
	}
	
	public IdCart(String idCart) {
		this.idCart = idCart;
	}
	
	public String getIdCart() {
		return idCart;
	}
}
