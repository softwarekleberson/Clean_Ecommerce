package com.cleancode.ecommerce.product.domain.product.bag;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Volume {

private String volume;
	
	public Volume(String volume) {
		if(volume == null || volume.trim().isBlank()) {
			throw new IllegalDomainException("Volume not be null");
		}
		
		this.volume = volume;
	}
	
	public String getVolume() {
		return volume;
	}
}
