package com.cleancode.ecommerce.domain.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.Midia;

public class MidiaTest {

	@Test
	void shouldCreateMidiaSucefull() {
		Midia midia = new Midia("https://example.com/image.jpg", "Test Description");

		assertTrue(midia.getId().matches("^[0-9a-fA-F\\-]{36}$"));
		assertEquals("https://example.com/image.jpg", midia.getUrl());
		assertEquals("Test Description", midia.getDescription());
	}

	@Test
	void shouldThrowExceptionWhenUrlIsInvalid() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> new Midia("invalid-url", "Descrição válida"));
		assertEquals("Url not be valid", exception.getMessage());
	}

	@Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        IllegalDomainException exception = assertThrows(
            IllegalDomainException.class,
            () -> new Midia("https://example.com/image.jpg", null)
        );
        assertEquals("Description not be valid", exception.getMessage());
    }

	@Test
	void shouldThrowExceptionWhenDescriptionIsBlank() {
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> new Midia("https://example.com/image.jpg", "   "));
		assertEquals("Description not be valid", exception.getMessage());
	}
}
