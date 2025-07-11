package com.cleancode.ecommerce.shared.domain.customer.event;

public abstract class Listener {

	public void process(Event event) {
		if(this.mustProcess(event)) {
			this.realgeTo(event);
		}
	}
	
	protected abstract boolean mustProcess(Event event);
	protected abstract void realgeTo(Event event);
}
