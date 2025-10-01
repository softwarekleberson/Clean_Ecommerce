package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public class UpdateNewReservationImpl implements UpdateNewReservation{

	@Override
	public Stock creteNewReservation(Stock stock, int quantity, String customerId, String cartId) {
		stock.reservation(cartId, customerId, quantity);
		return stock;
	}
}
