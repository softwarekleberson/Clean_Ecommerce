package com.cleancode.ecommerce.product.domain;

public class Description {

	private String description;
	
	public Description(String description) {
		if(description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description not be null");
		}
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
