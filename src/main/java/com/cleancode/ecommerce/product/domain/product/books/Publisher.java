package com.cleancode.ecommerce.product.domain.product.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Publisher {

	private final String publisher;
	
	public Publisher(String publisher) {
		if(publisher == null || publisher.trim().isEmpty()) {
			throw new IllegalDomainException("Publisher not be null");
		}
		
		this.publisher = publisher;
	}
	
	public String getPublisher() {
		return publisher;
	}
}
