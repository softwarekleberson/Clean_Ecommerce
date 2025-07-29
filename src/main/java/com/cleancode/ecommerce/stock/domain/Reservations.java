package com.cleancode.ecommerce.stock.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservations {

	private final IdStock reservationId;
	private final String cartId;
	private final String customerId;
	private final Quantity quantity;
	private final LocalDateTime reservationTime;
	private ReserveStatus reserveStatus;

	public Reservations(String cartId, String customerId, int quantity) {

		this.reservationId = new IdStock();
		this.cartId = cartId;
		this.customerId = customerId;
		this.quantity = new Quantity(quantity);
		this.reservationTime = LocalDateTime.now();
		this.reserveStatus = ReserveStatus.ACTIVE;
	}

	public void cancel() {
		this.reserveStatus = ReserveStatus.CANCELED;
	}

	public void confirmOrder() {
		this.reserveStatus = ReserveStatus.CONSUMED;
	}

	public IdStock getReservationId() {
		return reservationId;
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public ReserveStatus getReserveStatus() {
		return reserveStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, customerId, quantity, reservationId, reservationTime, reserveStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservations other = (Reservations) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(customerId, other.customerId)
				&& quantity == other.quantity && Objects.equals(reservationId, other.reservationId)
				&& Objects.equals(reservationTime, other.reservationTime) && reserveStatus == other.reserveStatus;
	}
}
