package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ValidateProductHasStockServiceImpl implements ValidateProductHasStock {

	@Override
	public ReservationResultDto reserve(Stock stock, int quantity, String customerId, String cartId) {
		if (stock.getTotalQuantity() < quantity) {
			throw new IllegalDomainException("Quantity of items greater than the product stocks");
		}

		var reservationId = stock.reservation(cartId, customerId, quantity);
		return new ReservationResultDto(stock, reservationId.getReservationId());
	}	
}