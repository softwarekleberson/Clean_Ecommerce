package com.cleancode.ecommerce.stock.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.stock.domain.exception.IllegalStockException;

public class Purchaseprice {

	private BigDecimal purchasePrice;

	public Purchaseprice(BigDecimal purchasePrice) {
		if (purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalStockException("the value purchase price need positive");
		}

		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
}