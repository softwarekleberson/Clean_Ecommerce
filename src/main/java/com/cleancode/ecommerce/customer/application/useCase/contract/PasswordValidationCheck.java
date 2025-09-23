package com.cleancode.ecommerce.customer.application.useCase.contract;

public interface PasswordValidationCheck {

	public void validateAcceptablePasswordFormat(String password);
	public void passwordCheckAndConfirmPassword(String password, String confirmPassword);
}
