package com.cleancode.ecommerce.customer.domain.customer;

public class Id {

	private final String value;
	
	public Id(String value) {
	     if (value == null) throw new IllegalArgumentException("id cannot be null");
	     this.value = value;
	}

	public String getValue() {
	      return value;
	}
}
