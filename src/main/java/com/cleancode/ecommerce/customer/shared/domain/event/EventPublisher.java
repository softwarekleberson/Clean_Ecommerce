package com.cleancode.ecommerce.customer.shared.domain.event;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

	private final List<Listener> listenir = new ArrayList<>();
	
	public void add(Listener listener) {
		this.listenir.add(listener);
	}
	
	public void process(Event event) {
		this.listenir.forEach(o -> o.process(event));
	}
}
