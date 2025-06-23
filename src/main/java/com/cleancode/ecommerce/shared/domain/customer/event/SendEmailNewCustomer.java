package com.cleancode.ecommerce.shared.domain.customer.event;

public class SendEmailNewCustomer extends Listener{

	@Override
	protected boolean mustProcess(Event event) {
		return event.type() == TypeEvent.CREATE_CUSTOMER;
	}

	@Override
	protected void realgeTo(Event event) {
		String name = (String) event.informations().get("name");
		String cpf = (String) event.informations().get("cpf");
		
		System.out.println("Sending welcome email to : " + name + " Cpf " + cpf);
	}
}