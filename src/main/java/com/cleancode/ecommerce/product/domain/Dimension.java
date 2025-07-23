package com.cleancode.ecommerce.product.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Dimension {

	public final int NUMBER_MIM_DIMENSION = 1;
	private final double height;
	private final double width;
	private final double length;
	private final double weight;

	public Dimension(double height, double width, double length, double weight) {
		if (height < NUMBER_MIM_DIMENSION) {
			throw new IllegalDomainException("Number page not be less than 0");
		}
		
		if (width < NUMBER_MIM_DIMENSION) {
			throw new IllegalDomainException("Width page not be less than 0");
		}
		
		if (length < NUMBER_MIM_DIMENSION) {
			throw new IllegalDomainException("Length page not be less than 0");
		}
		
		if (weight < NUMBER_MIM_DIMENSION) {
			throw new IllegalDomainException("Weight page not be less than 0");
		}
		
		this.height = height;
		this.width = width;
		this.length = length;
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	public double getWeight() {
		return weight;
	}
}