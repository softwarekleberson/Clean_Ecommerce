package com.cleancode.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.CartItens;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;
import com.cleancode.ecommerce.stock.domain.exception.IllegalStockException;

public class CartItensTest {
	private CartItemId cartItemId;
	private ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price unitPrice;
	private ReservationId reservationId;

	@BeforeEach
	void setUp() {
		cartItemId = new CartItemId(UUID.randomUUID().toString());
		productId = new ProductId(UUID.randomUUID().toString());
		productName = new Name("Clean Code Book");
		quantity = new Quantity(2);
		unitPrice = new Price(BigDecimal.valueOf(30), TypeCoin.DOLAR);
		reservationId = new ReservationId(UUID.randomUUID().toString());
	}

	@Test
	void shouldCreateCartItemSuccessfully() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		assertNotNull(item.getCartItemId());
		assertEquals(productId, item.getProductId());
		assertEquals(productName, item.getProductName());
		assertEquals(2, item.getQuantity().getQuantity());
		assertEquals(BigDecimal.valueOf(30), item.getUnitPrice().getPrice());
		assertEquals(BigDecimal.valueOf(60), item.getSubtotal().getPrice()); // 2 * 30
	}

	@Test
	void shouldThrowWhenAnyArgumentIsNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(null, productId, productName, quantity, unitPrice, reservationId));

		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(cartItemId, null, productName, quantity, unitPrice, reservationId));

		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(cartItemId, productId, null, quantity, unitPrice, reservationId));

		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(cartItemId, productId, productName, null, unitPrice, reservationId));

		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(cartItemId, productId, productName, quantity, null, reservationId));

		assertThrows(IllegalArgumentException.class,
				() -> new CartItens(cartItemId, productId, productName, quantity, unitPrice, null));
	}

	@Test
	void shouldCalculateSubtotalCorrectly() {
		CartItens item = new CartItens(cartItemId, productId, productName, new Quantity(5), unitPrice, reservationId);

		Price subtotal = item.calculateSubtotal();

		assertEquals(BigDecimal.valueOf(150), subtotal.getPrice()); // 5 * 30
		assertEquals(TypeCoin.DOLAR, subtotal.getCoin());
	}

	@Test
	void shouldIncreaseQuantity() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		item.increaseQuantity(new Quantity(3));

		assertEquals(5, item.getQuantity().getQuantity());
		assertEquals(BigDecimal.valueOf(150), item.getSubtotal().getPrice()); // (2+3) * 30
	}

	@Test
	void shouldThrowWhenIncreasingWithNonPositiveQuantity() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		assertThrows(IllegalStockException.class, () -> item.increaseQuantity(new Quantity(0)));
		assertThrows(IllegalStockException.class, () -> item.increaseQuantity(new Quantity(-2)));
	}

	@Test
	void shouldChangeQuantity() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		item.changeQuantity(new Quantity(10));

		assertEquals(10, item.getQuantity().getQuantity());
		assertEquals(BigDecimal.valueOf(300), item.getSubtotal().getPrice()); // 10 * 30
	}

	@Test
	void shouldThrowWhenChangingToNonPositiveQuantity() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		assertThrows(IllegalStockException.class, () -> item.changeQuantity(new Quantity(0)));
		assertThrows(IllegalStockException.class, () -> item.changeQuantity(new Quantity(-1)));
	}
}