package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public class ValidateProductHasStockServiceImpl implements ValidateProductHasStock {

	@Override
	public ReservationResultDto reserve(Stock stock, int quantity, String customerId, String cartId) {

		var reservation = stock.reservation(cartId, customerId, quantity);
		return new ReservationResultDto(stock, reservation.getReservationId());
	}
}