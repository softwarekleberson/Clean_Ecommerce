package com.cleancode.ecommerce.domain.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.Email;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;

class EmailTest {

	@Test
	void shouldNotCreateInvalidEmail() {
	
		assertThrows(IllegalContactException.class, () -> new Email(null));
		assertThrows(IllegalContactException.class, () -> new Email(""));
		assertThrows(IllegalContactException.class, () -> new Email("emailinvalido"));
	}
	
	@Test
	void shoudCreateEmailGoogle() {
		String validate = "antoniosilvacond@gmail.com";
		Email email = new Email(validate);
		assertEquals(validate, email.getEmail());
	}
	
	@Test
	void shoudCreateEmailLivre() {
		String validate = "antoniosilvacond@live.com";
		Email email = new Email(validate);
		assertEquals(validate, email.getEmail());
	}
}
