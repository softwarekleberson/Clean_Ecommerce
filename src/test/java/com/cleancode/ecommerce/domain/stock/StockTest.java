package com.cleancode.ecommerce.domain.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.ProductQuality;
import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.StockId;
import com.cleancode.ecommerce.stock.domain.exception.IllegalReservationException;

public class StockTest {
	private ProductId productId;
	private Price purchasePrice;

	@BeforeEach
	void setUp() {
		purchasePrice = new Price(BigDecimal.valueOf(10), TypeCoin.DOLAR);
	}

	@Test
	void shouldCreateStockWithProductId() {
		Stock stock = new Stock(productId);

		assertNotNull(stock.getStockId());
		assertEquals(productId, stock.getProductId());
		assertEquals(0, stock.getTotalQuantity());
		assertEquals(0, stock.getQuantityAvailable());
	}

	@Test
	void shouldCreateStockWithInitialQuantity() {
		StockId stockId = new StockId();
		Stock stock = new Stock(stockId, productId, 100);

		assertEquals(stockId, stock.getStockId());
		assertEquals(100, stock.getTotalQuantity());
		assertEquals(100, stock.getQuantityAvailable());
	}

	@Test
	void shouldAddProductInputSuccessfully() {
		Stock stock = new Stock(productId);

		stock.addProductInput(50, ProductQuality.NEW, purchasePrice, "Supplier A");

		assertEquals(50, stock.getTotalQuantity());
		assertEquals(50, stock.getQuantityAvailable());
		assertEquals(1, stock.getProductInput().size());
	}

	@Test
	void shouldThrowWhenAddingNonPositiveQuantity() {
		Stock stock = new Stock(productId);

		assertThrows(IllegalDomainException.class,
				() -> stock.addProductInput(0, ProductQuality.NEW, purchasePrice, "Supplier A"));
		assertThrows(IllegalDomainException.class,
				() -> stock.addProductInput(-5, ProductQuality.NEW, purchasePrice, "Supplier A"));
	}

	@Test
	void shouldReserveProductSuccessfully() {
		Stock stock = new Stock(productId);
		stock.addProductInput(20, ProductQuality.NEW, purchasePrice, "Supplier A");

		Reservations reservation = stock.reservation("cart-123", "cust-456", 5);

		assertNotNull(reservation);
		assertEquals(15, stock.getQuantityAvailable());
		assertEquals(1, stock.getReservations().size());
	}

	@Test
	void shouldThrowWhenReservingMoreThanAvailable() {
		Stock stock = new Stock(productId);
		stock.addProductInput(10, ProductQuality.NEW, purchasePrice, "Supplier A");

		assertThrows(IllegalReservationException.class, () -> stock.reservation("cart-1", "cust-1", 20));
	}

	@Test
	void shouldCancelReservationAndRestoreQuantity() {
		Stock stock = new Stock(productId);
		stock.addProductInput(10, ProductQuality.NEW, purchasePrice, "Supplier A");
		Reservations reservation = stock.reservation("cart-1", "cust-1", 5);

		stock.cancelReservation(reservation.getReservationId());

		assertEquals(10, stock.getQuantityAvailable());
	}

	@Test
	void shouldThrowWhenCancelingNonexistentReservation() {
		Stock stock = new Stock(productId);

		assertThrows(IllegalReservationException.class, () -> stock.cancelReservation("invalid-id"));
	}

	@Test
	void shouldConfirmOrderAndDecreaseTotalQuantity() {
		ProductId productId = new ProductId("p-123");
		Price purchasePrice = new Price(BigDecimal.valueOf(100.0));

		Stock stock = new Stock(productId);
		stock.addProductInput(10, ProductQuality.NEW, purchasePrice, "Supplier A");

		Reservations reservation = stock.reservation("cart-1", "cust-1", 5);

		stock.confirmOrder("order-1", productId.getProductId(), reservation.getReservationId());

		assertEquals(5, stock.getTotalQuantity());
		assertEquals(1, stock.getProductOutput().size());
	}

	@Test
	void shouldThrowWhenConfirmingNonexistentReservation() {
		ProductId productId = new ProductId("prod-1");
		Stock stock = new Stock(productId);

		assertThrows(IllegalReservationException.class,
				() -> stock.confirmOrder("order-1", productId.getProductId().toString(), "invalid-id"));
	}

	@Test
	void shouldFindReservationById() {
		Stock stock = new Stock(productId);
		stock.addProductInput(10, ProductQuality.NEW, purchasePrice, "Supplier A");
		Reservations reservation = stock.reservation("cart-1", "cust-1", 5);

		Reservations found = stock.getReservationId(reservation.getReservationId());

		assertEquals(reservation, found);
	}

	@Test
	void shouldThrowWhenReservationNotFoundById() {
		Stock stock = new Stock(productId);

		assertThrows(IllegalReservationException.class, () -> stock.getReservationId("invalid-id"));
	}
}