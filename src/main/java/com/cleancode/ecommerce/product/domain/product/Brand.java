package com.cleancode.ecommerce.product.domain.product;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Brand {

	private final String brand;
	
	public Brand(String brand) {
		if(brand == null || brand.trim().isEmpty()) {
			throw new IllegalDomainException("Brand not be null");
		}
		this.brand = brand;
	}
	
	public String getBrand() {
		return brand;
	}
}
