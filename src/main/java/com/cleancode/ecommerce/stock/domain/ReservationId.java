package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class ReservationId {

	private final String reservationId;
	
	public ReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	
	public ReservationId() {
		this.reservationId = UUID.randomUUID().toString();
	}
	
	public String getReservationId() {
		return reservationId;
	}
}
