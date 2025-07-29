package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class IdStock {

	private final String idStock;

	public IdStock() {
		this.idStock = UUID.randomUUID().toString();
	}

	public IdStock(String idStock) {
		this.idStock = idStock;
	}

	public String getIdStock() {
		return idStock;
	}
}