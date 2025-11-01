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
import com.cleancode.ecommerce.stock.domain.ProductQuality;
import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.ReserveStatus;
import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.exception.IllegalReservationException;

public class StockTest {

	private Stock stock;
	private ProductId productId;

	@BeforeEach
	void setUp() {
		productId = new ProductId();
		stock = new Stock(productId);
	}

	@Test
	void deveCriarStockComProduto() {
		assertNotNull(stock.getStockId());
		assertEquals(productId, stock.getProductId());
		assertEquals(0, stock.getTotalQuantity());
		assertEquals(0, stock.getQuantityAvailable());
	}

	@Test
	void deveAdicionarEntradaDeProdutoEAumentarQuantidade() {
		stock.addProductInput(10, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X");

		assertEquals(10, stock.getTotalQuantity());
		assertEquals(10, stock.getQuantityAvailable());
		assertEquals(1, stock.getProductInput().size());
	}

	@Test
	void deveLancarExcecaoQuandoEntradaComQuantidadeInvalida() {
		assertThrows(IllegalDomainException.class,
				() -> stock.addProductInput(0, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X"));
	}

	@Test
	void deveCriarReservaReducaoQuantidadeDisponivel() {
		stock.addProductInput(10, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X");

		Reservations r = stock.reservation("cart-123", "cust-1", 5);

		assertEquals(10, stock.getTotalQuantity());
		assertEquals(5, stock.getQuantityAvailable());
		assertEquals(1, stock.getReservations().size());
		assertEquals(ReserveStatus.ACTIVE, r.getReserveStatus());
	}

	@Test
	void deveLancarExcecaoQuandoReservaMaiorQueDisponivel() {
		stock.addProductInput(5, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X");

		assertThrows(IllegalReservationException.class, () -> stock.reservation("cart-123", "cust-1", 10));
	}

	@Test
	void deveCancelarReservaERestaurarQuantidade() {
		stock.addProductInput(10, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X");
		Reservations r = stock.reservation("cart-123", "cust-1", 5);

		stock.cancelReservation(r.getReservationId());

		assertEquals(10, stock.getTotalQuantity());
		assertEquals(10, stock.getQuantityAvailable());
		assertEquals(ReserveStatus.CANCELED, stock.getReservationId(r.getReservationId()).getReserveStatus());
	}

	@Test
	void deveConfirmarPedidoReduzindoTotal() {
		stock.addProductInput(10, ProductQuality.NEW, new Price(new BigDecimal("15.00")), "Fornecedor X");
		Reservations r = stock.reservation("cart-123", "cust-1", 5);

		stock.confirmOrder("order-1", productId.getProductId(), r.getReservationId());

		assertEquals(5, stock.getTotalQuantity());
		assertEquals(5, stock.getQuantityAvailable()); 
		assertEquals(1, stock.getProductOutput().size());
		assertEquals(ReserveStatus.CONSUMED, stock.getReservationId(r.getReservationId()).getReserveStatus());
	}
}