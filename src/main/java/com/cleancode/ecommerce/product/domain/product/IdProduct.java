package com.cleancode.ecommerce.product.domain.product;

import java.util.UUID;

public class IdProduct {

	private final UUID idProduct;
	
	public IdProduct() {
		this.idProduct = UUID.randomUUID();
	}
	
	public UUID getIdProduct() {
		return idProduct;
	}
}
