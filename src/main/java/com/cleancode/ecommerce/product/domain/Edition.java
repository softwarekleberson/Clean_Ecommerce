package com.cleancode.ecommerce.product.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Edition {

	private String edition;
	
	public Edition(String edition) {
		if(edition == null || edition.trim().isEmpty()) {
			throw new IllegalDomainException("Edition not be null");
		}
		this.edition = edition;
	}
	
	public String getEdition() {
		return edition;
	}
}
