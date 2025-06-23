package com.cleancode.ecommerce.product.domain.product.bag;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Color {

	private String color;
	
	public Color(String color) {
		if(color == null || color.trim().isBlank()) {
			throw new IllegalDomainException("Color not be null");
		}
		
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
}
