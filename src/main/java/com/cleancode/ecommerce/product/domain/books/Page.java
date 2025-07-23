package com.cleancode.ecommerce.product.domain.books;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Page {

	public final int NUMBER_MIM_PAGE = 1;
	private final int page;
	
	public Page(int page) {
		if(page < NUMBER_MIM_PAGE) {
			throw new IllegalDomainException("Number page not be less than 0");
		}
		this.page = page;
	}

	public int getPage() {
		return page;
	}
}