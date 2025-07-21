package com.cleancode.ecommerce.product.domain.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Page {

	private final String page;
	
	public Page(String page) {
		if(page == null || page.trim().isEmpty()) {
			throw new IllegalDomainException("Pages cannot be null or less than 0");
		}
		this.page = page;
	}
	
	public String getPage() {
		return page;
	}
}
