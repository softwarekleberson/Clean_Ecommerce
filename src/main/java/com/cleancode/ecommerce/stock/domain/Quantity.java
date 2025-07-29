package com.cleancode.ecommerce.stock.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Quantity {

	public static final int MIN_QUANTITY = 0;
	private int quantity;

	public Quantity(int quantity) {
		if(quantity <= MIN_QUANTITY) {
			throw new IllegalDomainException("quantity must have a value greater than 0");
		}
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
}
