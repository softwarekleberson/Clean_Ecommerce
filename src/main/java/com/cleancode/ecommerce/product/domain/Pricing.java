package com.cleancode.ecommerce.product.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Pricing {

	private BigDecimal pricing;

	public Pricing(BigDecimal pricing) {
		if (pricing == null) {
			throw new IllegalDomainException("Pricing cannot be null");
		}
		
		if (pricing.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalDomainException("Price must be a positive value");
		}

		this.pricing = pricing;
	}

	public BigDecimal getPricing() {
		return pricing;
	}
}