package com.cleancode.ecommerce.customer.domain.customer.event;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.cleancode.ecommerce.customer.domain.customer.Name;
import com.cleancode.ecommerce.customer.shared.domain.Cpf;
import com.cleancode.ecommerce.customer.shared.domain.event.Event;
import com.cleancode.ecommerce.customer.shared.domain.event.TypeEvent;

public class EventNewCustomer implements Event{

	private final LocalDateTime moment;
	private final Cpf cpf;
	private final Name name;
	
	public EventNewCustomer(Cpf cpf, Name name) {
		
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