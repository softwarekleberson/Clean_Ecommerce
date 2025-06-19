package com.cleancode.ecommerce.customer.domain;

import java.util.UUID;

public class CustomerId {

	private final UUID value;
	
	public CustomerId(UUID value) {
	     if (value == null) throw new IllegalArgumentException("id cannot be null");
	     this.value = value;
	}

	public UUID getValue() {
	      return value;
	}
}
