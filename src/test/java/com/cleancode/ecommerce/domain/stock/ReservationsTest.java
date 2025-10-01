package com.cleancode.ecommerce.domain.stock;

import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.ReserveStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationsTest {

	@Test
	@DisplayName("Creating reservation with basic constructor sets default values correctly")
	void constructor_setsDefaults() {
		Reservations r = new Reservations("cart-1", "cust-1", 5);

		assertNotNull(r.getReservationId());
		assertEquals("cart-1", r.getCartId().getCartId());
		assertEquals("cust-1", r.getCustomerId().getValue());
		assertEquals(5, r.getQuantity());
		assertNotNull(r.getReservationTime());
		assertEquals(ReserveStatus.ACTIVE, r.getReserveStatus());
	}

	@Test
	@DisplayName("Creating reservation with full constructor preserves provided values")
	void fullConstructor_preservesValues() {
		LocalDateTime now = LocalDateTime.now();
		Reservations r = new Reservations("res-123", "cart-1", "cust-1", 7, now, ReserveStatus.CANCELED);

		assertEquals("res-123", r.getReservationId());
		assertEquals("cart-1", r.getCartId().getCartId());
		assertEquals("cust-1", r.getCustomerId().getValue());
		assertEquals(7, r.getQuantity());
		assertEquals(now, r.getReservationTime());
		assertEquals(ReserveStatus.CANCELED, r.getReserveStatus());
	}

	@Test
	@DisplayName("Canceling reservation changes status to CANCELED")
	void cancel_changesStatus() {
		Reservations r = new Reservations("cart-1", "cust-1", 3);
		r.cancel();
		assertEquals(ReserveStatus.CANCELED, r.getReserveStatus());
	}

	@Test
	@DisplayName("Confirming order changes status to CONSUMED")
	void confirmOrder_changesStatus() {
		Reservations r = new Reservations("cart-1", "cust-1", 4);
		r.confirmOrder();
		assertEquals(ReserveStatus.CONSUMED, r.getReserveStatus());
	}

	@Test
	@DisplayName("equals and hashCode use reservationId only")
	void equalsAndHashCode_basedOnReservationId() {
		Reservations r1 = new Reservations("cart-1", "cust-1", 2);
		Reservations r2 = new Reservations(r1.getReservationId(), "cart-2", "cust-2", 9, r1.getReservationTime(),
				ReserveStatus.ACTIVE);

		assertEquals(r1, r2);
		assertEquals(r1.hashCode(), r2.hashCode());
	}

	@Test
	@DisplayName("equals returns false for different reservationId")
	void equals_returnsFalseForDifferentId() {
		Reservations r1 = new Reservations("cart-1", "cust-1", 2);
		Reservations r2 = new Reservations("cart-2", "cust-2", 2);
		assertNotEquals(r1, r2);
	}
}