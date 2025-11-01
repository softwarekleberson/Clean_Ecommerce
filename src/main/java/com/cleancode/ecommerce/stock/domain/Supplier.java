package com.cleancode.ecommerce.stock.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Supplier {

	private String supplier;

	public Supplier(String supplier) {
		if (supplier == null || supplier.isBlank()) {
			throw new IllegalDomainException("supplier needs be information");
		}
		this.supplier = supplier;
	}

	public String getSupplier() {
		return supplier;
	}
}
