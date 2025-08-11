package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class StockId {

	private final String stockId;

	public StockId() {
		this.stockId = UUID.randomUUID().toString();
	}

	public StockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockId() {
		return stockId;
	}
}