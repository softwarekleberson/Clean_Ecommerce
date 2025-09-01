package com.cleancode.ecommerce.stock.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.order.domain.cart.CartId;

public class Reservations {

	private final ReservationId reservationId;
	private final CartId cartId;
	private final CustomerId customerId;
	private final Quantity quantity;
	private final LocalDateTime reservationTime;
	private ReserveStatus reserveStatus;
	
	public Reservations(String cartId, String customerId, int quantity) {

		this.reservationId = new ReservationId();
		this.cartId = new CartId(cartId);
		this.customerId = new CustomerId(customerId);
		this.quantity = new Quantity(quantity);
		this.reservationTime = LocalDateTime.now();
		this.reserveStatus = ReserveStatus.ACTIVE;
	}

	public Reservations(String reservationId, String cartId, String customerId, int quantity) {

		this.reservationId = new ReservationId(reservationId);
		this.cartId = new CartId(cartId);
		this.customerId = new CustomerId(customerId);
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
	
	public String getReservationId() {
		return reservationId.getReservationId();
	}

	public CartId getCartId() {
		return cartId;
	}

	public CustomerId getCustomerId() {
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
		return Objects.hash(cartId, customerId, quantity, reservationTime, reserveStatus);
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
				&& quantity == other.quantity && Objects.equals(reservationTime, other.reservationTime) && reserveStatus == other.reserveStatus;
	}
}
