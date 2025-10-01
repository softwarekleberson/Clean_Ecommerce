package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public class CancelProductStockReservationImpl implements CancelProductStockReservation {

	@Override
	public Stock cancelReservationAfterDelete(Stock stock, String reservationId) {
		stock.cancelReservation(reservationId);
		return stock;
	}
}
