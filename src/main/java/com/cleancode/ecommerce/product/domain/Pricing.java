package com.cleancode.ecommerce.product.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Pricing {

	private BigDecimal pricing;

	public Pricing(BigDecimal pricing) {
		if (pricing == null) {
			throw new IllegalDomainException("Pricing cannot be null");
		}
		
		if (pricing.compareTo(BigDecimal.ZERO) < 0 || pricing.compareTo(BigDecimal.ONE) > 0) {
		    throw new IllegalDomainException("Pricing must be between 0 and 1 inclusive");
		}

		this.pricing = pricing;
	}
	
	public BigDecimal getPricing() {
		return pricing;
	}
}