package com.cleancode.ecommerce.customer.domain.customer;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;

public class Email {

	private String email;
	
	public Email(String email) {
		if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			throw new IllegalContactException("Email is incorrect");
		}
		
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Email [email=" + email + "]";
	}	
}