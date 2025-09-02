package com.cleancode.ecommerce.payment.domain.card;

import com.cleancode.ecommerce.payment.domain.card.exception.IllegalCardException;

public class PrintedName {

	private String name;
	
	public PrintedName(String name) {
		if(name == null || name.isBlank()) {
			throw new IllegalCardException("Printed name is required in card");
		}
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
