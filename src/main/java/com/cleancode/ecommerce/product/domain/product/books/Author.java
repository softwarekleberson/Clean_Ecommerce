package com.cleancode.ecommerce.product.domain.product.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Author {

	private final String author;
	
	public Author(String author) {
		if(author == null || author.trim().isEmpty()) {
			throw new IllegalDomainException("Author not ne null");
		}
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
}
