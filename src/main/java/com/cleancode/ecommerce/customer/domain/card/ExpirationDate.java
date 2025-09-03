package com.cleancode.ecommerce.customer.domain.card;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.card.exception.IllegalCardException;

public class ExpirationDate {

	private LocalDate expirationDate;

	public ExpirationDate(LocalDate expirationDate) {
		if (expirationDate.isBefore(LocalDate.now())) {
			throw new IllegalCardException("Expiration date cannot be in the past");
		}
		this.expirationDate = expirationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}
}
