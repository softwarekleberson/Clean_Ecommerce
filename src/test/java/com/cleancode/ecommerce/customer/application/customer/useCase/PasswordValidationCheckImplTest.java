package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.useCase.PasswordValidationCheckImpl;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationCheckImplTest {

	private PasswordValidationCheckImpl passwordValidator;

	@BeforeEach
	void setUp() {
		passwordValidator = new PasswordValidationCheckImpl();
	}

	@Test
	void shouldPassWhenPasswordsMatch() {
		assertDoesNotThrow(() -> passwordValidator.passwordCheckAndConfirmPassword("Password123!", "Password123!"));
	}

	@Test
	void shouldThrowWhenPasswordsDoNotMatch() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.passwordCheckAndConfirmPassword("Password123!", "Password123"));

		assertEquals("password does not match confirm password", exception.getMessage());
	}

	@Test
	void shouldPassForValidPassword() {
		assertDoesNotThrow(() -> passwordValidator.validateAcceptablePasswordFormat("Valid123!"));
	}

	@Test
	void shouldThrowWhenPasswordTooShort() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.validateAcceptablePasswordFormat("Ab1!"));
		assertEquals("password must contain at least 8 characters", exception.getMessage());
	}

	@Test
	void shouldThrowWhenMissingUppercase() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.validateAcceptablePasswordFormat("valid123!"));
		assertEquals("Password must contain at least one uppercase letter", exception.getMessage());
	}

	@Test
	void shouldThrowWhenMissingLowercase() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.validateAcceptablePasswordFormat("VALID123!"));
		assertEquals("Password must contain at least one lowercase letter", exception.getMessage());
	}

	@Test
	void shouldThrowWhenMissingDigit() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.validateAcceptablePasswordFormat("ValidPass!"));
		assertEquals("Password must contain at least one digit", exception.getMessage());
	}

	@Test
	void shouldThrowWhenMissingSpecialCharacter() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> passwordValidator.validateAcceptablePasswordFormat("Valid1234"));
		assertEquals("Password must contain at least one special character", exception.getMessage());
	}
}
