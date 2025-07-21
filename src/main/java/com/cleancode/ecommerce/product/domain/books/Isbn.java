package com.cleancode.ecommerce.product.domain.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Isbn {

	private final String isbn;
	
	public Isbn(String isbn) {
		String ISBN_REGEX = "^(?:\\d{9}X|\\d{10}|97[89]\\d{10})$";
		if(isbn.matches(ISBN_REGEX)) {
			throw new IllegalDomainException("Isbn not be valid");
		}
		
		this.isbn = isbn;
	}
	
	public String getIsbn() {
		return isbn;
	}
}
