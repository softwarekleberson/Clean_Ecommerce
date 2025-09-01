package com.cleancode.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.CartItens;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
public class CartItensTest {

	private CartItemId cartItemId;
	private ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price unitPrice;

	@BeforeEach
	void setUp() {
		cartItemId = new CartItemId("cartItemId-123");
		productId = new ProductId("prod-123");
		productName = new Name("Smartphone");
		quantity = new Quantity(2);
		unitPrice = new Price(new BigDecimal("50.00"), TypeCoin.DOLAR);
	}

	@Test
	void shouldCreateCartItemSuccessfully() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice);

		assertEquals(productId, item.getProductId());
		assertEquals(productName, item.getProductName());
		assertEquals(quantity.getQuantity(), item.getQuantity().getQuantity());
		assertEquals(unitPrice, item.getUnitPrice());
	}

	@Test
	void shouldThrowExceptionWhenCreatingCartItemWithNullValues() {
		assertThrows(IllegalArgumentException.class, () -> new CartItens(null ,productId, productName, quantity, unitPrice));
		assertThrows(IllegalArgumentException.class, () -> new CartItens(cartItemId, null, productName, quantity, unitPrice));
		assertThrows(IllegalArgumentException.class, () -> new CartItens(cartItemId ,productId, null, quantity, unitPrice));
		assertThrows(IllegalArgumentException.class, () -> new CartItens(cartItemId ,productId, productName, null, unitPrice));
		assertThrows(IllegalArgumentException.class, () -> new CartItens(cartItemId ,productId, productName, quantity, null));
	}

	@Test
	void shouldCalculateSubtotalCorrectly() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice);
		Price subtotal = item.calculateSubtotal();

		assertEquals(new BigDecimal("100.00"), subtotal.getPrice());
		assertEquals(TypeCoin.DOLAR, subtotal.getCoin());
	}

	@Test
	void shouldIncreaseQuantityCorrectly() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice);
		item.increaseQuantity(new Quantity(3));

		assertEquals(5, item.getQuantity().getQuantity());
		assertEquals(new BigDecimal("250.00"), item.getSubtotal().getPrice());
	}

	@Test
	void shouldThrowExceptionWhenIncreaseQuantityWithNonPositive() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice);
		assertThrows(IllegalDomainException.class, () -> item.increaseQuantity(new Quantity(0)));
		assertThrows(IllegalDomainException.class, () -> item.increaseQuantity(new Quantity(-2)));
	}

	@Test
	void shouldChangeQuantityCorrectly() {
		CartItens item = new CartItens(cartItemId, productId, productName, quantity, unitPrice);
		item.changeQuantity(new Quantity(10));

		assertEquals(10, item.getQuantity().getQuantity());
		assertEquals(new BigDecimal("500.00"), item.getSubtotal().getPrice());
	}

	@Test
	void shouldThrowExceptionWhenChangeQuantityToInvalidValue() {
		CartItens item = new CartItens(cartItemId ,productId, productName, quantity, unitPrice);
		assertThrows(IllegalDomainException.class, () -> item.changeQuantity(new Quantity(0)));
		assertThrows(IllegalDomainException.class, () -> item.changeQuantity(new Quantity(-1)));
	}
}