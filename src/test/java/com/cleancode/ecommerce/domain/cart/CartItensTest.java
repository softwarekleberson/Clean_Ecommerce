package com.cleancode.ecommerce.domain.cart;

import com.cleancode.ecommerce.cart.domain.CartItens;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CartItensTest {

	private ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price unitPrice;
	private CartItens cartItem;

	@BeforeEach
	void setUp() {
		productId = new ProductId("prod-1");
		productName = new Name("Produto Teste");
		quantity = new Quantity(3);
		unitPrice = new Price(BigDecimal.valueOf(15), TypeCoin.DOLAR);
		cartItem = new CartItens(productId, productName, quantity, unitPrice);
	}

	@Test
	void shouldCalculateCorrectSubtotal() {
		Price subtotal = cartItem.getSubtotal();

		assertEquals(new BigDecimal("45"), subtotal.getPrice());
		assertEquals(TypeCoin.DOLAR, subtotal.getCoin());
	}

	@Test
	void shouldRecalculateSubtotalWhenCalled() {
		Price newPrice = cartItem.calculeteSubtotal();
		assertEquals(new BigDecimal("45"), newPrice.getPrice());
	}

	@Test
	void shouldReturnCorrectProductId() {
		assertEquals(productId, cartItem.getProductId());
		assertEquals("prod-1", cartItem.getProductId().getProductId());
	}

	@Test
	void shouldReturnCorrectProductName() {
		assertEquals(productName, cartItem.getProductName());
		assertEquals("Produto Teste", cartItem.getProductName().getName());
	}

	@Test
	void shouldReturnCorrectQuantity() {
		assertEquals(quantity, cartItem.getQuantity());
		assertEquals(3, cartItem.getQuantity().getQuantity());
	}

	@Test
	void shouldReturnCorrectUnitPrice() {
		assertEquals(unitPrice, cartItem.getUnitPrice());
		assertEquals(new BigDecimal("15"), cartItem.getUnitPrice().getPrice());
	}
}