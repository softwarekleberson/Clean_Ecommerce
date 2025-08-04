package com.cleancode.ecommerce.stock.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.IdCustomer;

public class Reservations {

	private final IdStock id;
	private final IdCart cartId;
	private final IdCustomer customerId;
	private final Quantity quantity;
	private final LocalDateTime reservationTime;
	private ReserveStatus reserveStatus;

	public Reservations(String id, String cartId, String customerId, int quantity) {

		this.id = new IdStock(id);
		this.cartId = new IdCart(cartId);
		this.customerId = new IdCustomer(customerId);
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

	public String getId() {
		return id.toString();
	}

	public IdCart getCartId() {
		return cartId;
	}

	public IdCustomer getCustomerId() {
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
		return Objects.hash(cartId, customerId, quantity, id, reservationTime, reserveStatus);
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
				&& quantity == other.quantity && Objects.equals(id, other.id)
				&& Objects.equals(reservationTime, other.reservationTime) && reserveStatus == other.reserveStatus;
	}
}
