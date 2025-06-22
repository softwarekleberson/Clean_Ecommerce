package com.cleancode.ecommerce.product.domain.product;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Dimension {

	private final String height;
	private final String width;
	private final String length;
	private final String weight;
	
	public Dimension(String height, String width, String length, String weight) {
		
		if(height == null || height.trim().isEmpty()) {
			throw new IllegalDomainException("Height not be null");
		}
		
		if(width == null || width.trim().isEmpty()) {
			throw new IllegalDomainException("Width not be null");
		}
		
		if(length == null || length.trim().isEmpty()) {
			throw new IllegalDomainException("Length not be null");
		}
		
		if(weight == null || weight.trim().isEmpty()) {
			throw new IllegalDomainException("Weight not be null");
		}
		
		this.height = height;
		this.width = width;
		this.length = length;
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public String getWidth() {
		return width;
	}

	public String getLength() {
		return length;
	}

	public String getWeight() {
		return weight;
	}
}