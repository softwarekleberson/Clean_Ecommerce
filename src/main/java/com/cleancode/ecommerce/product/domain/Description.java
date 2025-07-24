package com.cleancode.ecommerce.product.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Description {

	private String description;
	
	public Description(String description) {
		if(description == null || description.trim().isEmpty()) {
			throw new IllegalDomainException("Description not be null");
		}
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
