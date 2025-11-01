package com.cleancode.ecommerce.event;

import java.time.Instant;

public class NewCustomerEvent implements DomainEvent{

	private final String name;
	private final String email;
	private final Instant occuredOn = Instant.now();
	
	public NewCustomerEvent(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public Instant getOccuredOn() {
		return occuredOn;
	}

	@Override
	public Instant occurredOn() {
		return occuredOn;
	}
}