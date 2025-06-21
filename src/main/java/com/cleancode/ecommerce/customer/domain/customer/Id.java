package com.cleancode.ecommerce.customer.domain.customer;

import java.util.UUID;

public class Id {

	private final UUID value;
	
	public Id(UUID value) {
	     if (value == null) throw new IllegalArgumentException("id cannot be null");
	     this.value = value;
	}

	public UUID getValue() {
	      return value;
	}
}
