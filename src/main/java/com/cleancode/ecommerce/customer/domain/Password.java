package com.cleancode.ecommerce.customer.domain;

public class Password {

	private String password;
	
	public Password(String password) {
		validateFormatPassword(password);
	}
	
	private void validateFormatPassword(String password) {
		if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{16}$")) {
		    throw new IllegalArgumentException("Senha inválida");
		}
		
		passwordStrengthValidation(password);
	}
	
	private void passwordStrengthValidation(String password) {
		
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
