package com.cleancode.ecommerce.product.domain.product;

import java.math.BigDecimal;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Price {

	public static final BigDecimal LOWEST_PRICE = BigDecimal.ZERO;
	private BigDecimal price;
	
	public Price(BigDecimal price) {
		if(price.compareTo(LOWEST_PRICE) <= 0) {
			throw new IllegalDomainException("Price must not be less than or equal to 0");
		}
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public static Price updatePrice(BigDecimal newPrice) {
		return new Price(newPrice);
	}
}
