package com.cleancode.ecommerce.domain.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.IdProduct;
import com.cleancode.ecommerce.stock.domain.ProductQuality;
import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.ReserveStatus;
import com.cleancode.ecommerce.stock.domain.Stock;

public class StockTest {

	private IdProduct idProduct;
	private Stock stock;

	@BeforeEach
	void setUp() {
		idProduct = new IdProduct();
		stock = new Stock(idProduct);
	}

	@Test
	void mustCreateStockWithInitialValue() {
		assertTrue(stock.getReservations().isEmpty());
		assertTrue(stock.getProductInput().isEmpty());
		assertTrue(stock.getProductOutput().isEmpty());
	}

	@Test
	void mustAddProductToStock() {
		stock.addProductInput(5, ProductQuality.NEW, BigDecimal.valueOf(100), "suplier x");
		assertEquals(5, stock.getTotalQuantity());
		assertEquals(5, stock.getQuantityAvailable());
		assertEquals(1, stock.getProductInput().size());
	}

	@Test
	void shouldThrowExceptionWhenAddingInvalidQuantity() {
		assertThrows(IllegalDomainException.class, () -> {
			stock.addProductInput(0, ProductQuality.NEW, BigDecimal.valueOf(100), "suplier x");
		});
	}

	@Test
	void mustCreateReserveandReduceAvailableQuantity() {
		stock.addProductInput(10, ProductQuality.NEW, BigDecimal.valueOf(100), "suplier x");
		stock.reservation(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 5);
		assertEquals(5, stock.getQuantityAvailable());
		assertEquals(1, stock.getReservations().size());
	}

	@Test
	void shouldThrowExceptionIfThereIsNotEnoughStockForReservation() {
		assertThrows(IllegalDomainException.class, () -> {
			stock.reservation(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 12);
		});
	}

	@Test
	void mustCancelReservationRestoreAvailableQuantity() {
		stock.addProductInput(10, ProductQuality.NEW, BigDecimal.valueOf(100), "suplier x");
		Reservations reservations = stock.reservation(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 5);
		stock.cancelReservation(reservations.getId());
		Reservations reserveStatus = stock.getReservationId(reservations.getId());

		assertEquals(10, stock.getQuantityAvailable());
		assertEquals(10, stock.getTotalQuantity());
		assertEquals(ReserveStatus.CANCELED, reserveStatus.getReserveStatus());
	}

	@Test
	void shouldThrowExceptionWhenCancelingNonExistentReservation() {
		assertThrows(IllegalDomainException.class, () -> stock.cancelReservation("abc"));
	}

	@Test
	void mustConfirmOrderReduceTotalStockRegisterOutput() {
		stock.addProductInput(10, ProductQuality.NEW, BigDecimal.valueOf(100), "xpto");
		Reservations reservations = stock.reservation(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 5);
		stock.confirmOrder(UUID.randomUUID().toString(), UUID.randomUUID().toString(), reservations.getId());
		Reservations reserveStatus = stock.getReservationId(reservations.getId());

		assertEquals(5, stock.getTotalQuantity());
		assertEquals(1, stock.getProductOutput().size());
		assertEquals(ReserveStatus.CONSUMED, reserveStatus.getReserveStatus());
	}

	@Test
	void shouldThrowExceptionWhenConfirmingOrderWithNonExistentReservation() {
		assertThrows(IllegalDomainException.class, () -> stock.confirmOrder("orderX", "oo" ,"resNaoExiste"));
	}
}