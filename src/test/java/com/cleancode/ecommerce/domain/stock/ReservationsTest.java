package com.cleancode.ecommerce.domain.stock;

import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.ReserveStatus;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationsTest {

	@Test
	void shouldCreateReservationWithGeneratedId() {
		Reservations reservation = new Reservations("cart-1", "cust-1", 5);

		assertNotNull(reservation.getReservationId());
		assertEquals("cart-1", reservation.getCartId().getCartId());
		assertEquals("cust-1", reservation.getCustomerId().getValue());
		assertEquals(5, reservation.getQuantity().getQuantity());
		assertEquals(ReserveStatus.ACTIVE, reservation.getReserveStatus());
		assertTrue(reservation.getReservationTime().isBefore(LocalDateTime.now().plusSeconds(1)));
	}

	@Test
	void shouldCreateReservationWithProvidedId() {
		Reservations reservation = new Reservations("res-123", "cart-1", "cust-1", 10);

		assertEquals("res-123", reservation.getReservationId());
		assertEquals("cart-1", reservation.getCartId().getCartId());
		assertEquals("cust-1", reservation.getCustomerId().getValue());
		assertEquals(10, reservation.getQuantity().getQuantity());
		assertEquals(ReserveStatus.ACTIVE, reservation.getReserveStatus());
	}

	@Test
	void shouldCancelReservation() {
		Reservations reservation = new Reservations("cart-1", "cust-1", 5);

		reservation.cancel();

		assertEquals(ReserveStatus.CANCELED, reservation.getReserveStatus());
	}

	@Test
	void shouldConfirmReservation() {
		Reservations reservation = new Reservations("cart-1", "cust-1", 5);

		reservation.confirmOrder();

		assertEquals(ReserveStatus.CONSUMED, reservation.getReserveStatus());
	}

	@Test
	void shouldTestEqualsAndHashCode() {
		Reservations r1 = new Reservations("cart-1", "cust-1", 5);
		Reservations r2 = new Reservations(r1.getReservationId(), "cart-1", "cust-1", 5);
		
		assertNotEquals(r1, r2);
		assertNotEquals(r1.hashCode(), 0);
	}

	@Test
	void shouldNotBeEqualToDifferentObject() {
		Reservations reservation = new Reservations("cart-1", "cust-1", 5);

		assertNotEquals(reservation, null);
		assertNotEquals(reservation, new Object());
	}
}