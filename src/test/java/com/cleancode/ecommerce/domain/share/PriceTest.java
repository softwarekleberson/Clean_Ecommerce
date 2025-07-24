package com.cleancode.ecommerce.domain.customer;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class PriceTest {

	@Test
	void shouldCreatePriceSuccessfully() {
		BigDecimal validPrice = new BigDecimal("10.00");
		TypeCoin coin = TypeCoin.DOLAR;

		Price price = new Price(validPrice, coin);

		assertEquals(validPrice, price.getPrice());
		assertEquals(coin, price.getCoin());
	}

	@Test
	void shouldThrowExceptionWhenPriceIsZero() {
		BigDecimal invalidPrice = BigDecimal.ZERO;
		TypeCoin coin = TypeCoin.LIBRA;

		assertThrows(IllegalDomainException.class, () -> new Price(invalidPrice, coin));
	}

	@Test
	void shouldThrowExceptionWhenPriceIsNegative() {
		BigDecimal invalidPrice = new BigDecimal("-5.00");
		TypeCoin coin = TypeCoin.EURO;

		assertThrows(IllegalDomainException.class, () -> new Price(invalidPrice, coin));
	}

	@Test
	void shouldUpdatePriceSuccessfully() {
		BigDecimal newPrice = new BigDecimal("20.50");
		TypeCoin coin = TypeCoin.DOLAR;

		Price updated = new Price(newPrice, coin);

		assertEquals(newPrice, updated.getPrice());
		assertEquals(coin, updated.getCoin());
	}
}
