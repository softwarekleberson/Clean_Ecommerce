package com.cleancode.ecommerce.product.domain.books;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class PublisherDate {

	private final LocalDate publisherDate;
	
	public PublisherDate(LocalDate publisherDate) {
		if(publisherDate.isAfter(LocalDate.now())) {
			throw new IllegalDomainException("Date publisher Invalid");
		}
		
		this.publisherDate = publisherDate;
	}
	
	public LocalDate getPublisherDate() {
		return publisherDate;
	}
}
