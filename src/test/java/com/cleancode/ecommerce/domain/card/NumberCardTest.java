package com.cleancode.ecommerce.domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.payment.domain.card.NumberCard;
import com.cleancode.ecommerce.payment.domain.card.exception.IllegalCardException;

public class NumberCardTest {

	@Test
	void shouldCreateNumberCardWhenValid() {

		String validNumber = "4539578763621486";
		NumberCard numberCard = new NumberCard(validNumber);

		assertNotNull(numberCard);
		assertEquals(validNumber, numberCard.getNumberCard());
	}

	@Test
	void shouldThrowExceptionWhenNumberCardIsInvalid() {
		String invalidNumber = "1234567812345678";

		Exception exception = assertThrows(IllegalCardException.class, () -> {
			new NumberCard(invalidNumber);
		});

		assertEquals("Number card required 16 numbers valid", exception.getMessage());
	}

	@Test
	void shouldThrowExceptionWhenNumberCardHasLessThan16Digits() {
		String shortNumber = "453957876362148";

		Exception exception = assertThrows(IllegalCardException.class, () -> {
			new NumberCard(shortNumber);
		});

		assertEquals("Number card required 16 numbers valid", exception.getMessage());
	}

	@Test
	void shouldThrowExceptionWhenNumberCardHasMoreThan16Digits() {
		String longNumber = "45395787636214867";

		Exception exception = assertThrows(IllegalCardException.class, () -> {
			new NumberCard(longNumber);
		});

		assertEquals("Number card required 16 numbers valid", exception.getMessage());
	}
}
