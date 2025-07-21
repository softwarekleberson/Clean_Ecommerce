package com.cleancode.ecommerce.product.domain;

import java.util.UUID;

public class IdProduct {

	private final String idProduct;
	
	public IdProduct() {
		this.idProduct = UUID.randomUUID().toString();
	}
	
	public String getIdProduct() {
		return idProduct;
	}
}
