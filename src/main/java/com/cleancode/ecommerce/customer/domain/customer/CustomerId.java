package com.cleancode.ecommerce.customer.domain.customer;

public class CustomerId {

	private final String value;
	
	public CustomerId(String value) {
	     if (value == null) throw new IllegalArgumentException("id cannot be null");
	     this.value = value;
	}

	public String getValue() {
	      return value;
	}
}
