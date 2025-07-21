package com.cleancode.ecommerce.product.domain.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Synopsis {

	private final String synopsis;
	
	public Synopsis(String synopsis) {
		if(synopsis == null || synopsis.trim().isEmpty()) {
			throw new IllegalDomainException("Synopsis not be null");
		}
		
		this.synopsis = synopsis;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
}
