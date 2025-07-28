package com.cleancode.ecommerce.product.domain;

import java.util.UUID;

public class IdProduct {

	private final String idProduct;

	public IdProduct() {
		this.idProduct = UUID.randomUUID().toString();
	}

	public IdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getIdProduct() {
		return idProduct;
	}
}
