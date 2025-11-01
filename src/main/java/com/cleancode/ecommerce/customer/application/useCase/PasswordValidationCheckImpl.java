package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.PasswordValidationCheck;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class PasswordValidationCheckImpl implements PasswordValidationCheck{

	public void passwordCheckAndConfirmPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword))
			throw new IllegalDomainException("password does not match confirm password");
	}
	
	@Override
	public void validateAcceptablePasswordFormat(String password) {
		final int MINIMUM_PASSWORD_SIZE = 8;
		if (password.isBlank() || password.length() < MINIMUM_PASSWORD_SIZE) {
			throw new IllegalDomainException("password must contain at least 8 characters");
		}

		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c))
				hasUpper = true;
			if (Character.isLowerCase(c))
				hasLower = true;
			if (Character.isDigit(c))
				hasDigit = true;
			if (isSpecialChar(c))
				hasSpecial = true;
		}

		if (!hasUpper) {
			throw new IllegalDomainException("Password must contain at least one uppercase letter");
		}
		if (!hasLower) {
			throw new IllegalDomainException("Password must contain at least one lowercase letter");
		}
		if (!hasDigit) {
			throw new IllegalDomainException("Password must contain at least one digit");
		}
		if (!hasSpecial) {
			throw new IllegalDomainException("Password must contain at least one special character");
		}
	}

	private boolean isSpecialChar(char c) {
		final String specialChars = "!@#$%^&*()_+-=[]{};:'\"\\|,.<>/?";
		return specialChars.indexOf(c) >= 0;
	}
}