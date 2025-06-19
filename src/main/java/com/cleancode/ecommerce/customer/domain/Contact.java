package com.cleancode.ecommerce.customer.domain;

public class Contact {

	private Phone phone;
	private Email email;
	
	public Contact(Phone phone, Email email) {
		this.phone = phone;
		this.email = email;
	}
	
	public String getFullPhone() {
		return phone.getDdd() + phone.getPhone();
	}
	
	public String getEmail() {
		return email.getEmail();
	}
}
