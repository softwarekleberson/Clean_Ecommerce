package com.cleancode.ecommerce.customer.shared.domain.event;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EventNewCustomer implements Event{

	private final LocalDateTime moment;
	private final String cpf;
	private final String name;
	
	public EventNewCustomer(LocalDateTime moment, String cpf, String name) {
		
		this.moment = LocalDateTime.now();
		this.cpf = cpf;
		this.name = name;
	}

	@Override
	public LocalDateTime moment() {
		return this.moment;
	}

	@Override
	public TypeEvent type() {
		return TypeEvent.CREATE_CUSTOMER;
	}

	@Override
	public Map<String, Object> informations() {
		Map<String, Object> date = new HashMap<>();
		date.put("cpf", cpf);
		date.put("name", name);
		return date;
	}
}
