package com.cleancode.ecommerce.stock.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Purchaseprice {

	private BigDecimal purchasePrice;

	public Purchaseprice(BigDecimal purchasePrice) {
		if (purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalDomainException("the value purchase price need positive");
		}

		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
}