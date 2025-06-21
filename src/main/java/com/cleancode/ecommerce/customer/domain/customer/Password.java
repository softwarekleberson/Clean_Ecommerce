package com.cleancode.ecommerce.customer.domain.customer;

public class Password {

	private String password;
	
	public Password(String password) {
		validateFormatPassword(password);
	}
	
	private void validateFormatPassword(String password) {
		if(password == null || password.trim().isEmpty()) {
			 throw new IllegalArgumentException("Password not be empty");
		}
		
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
