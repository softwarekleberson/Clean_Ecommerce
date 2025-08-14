package com.cleancode.ecommerce.cart.application.service;

import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ValidateProductHasStockServiceImpl implements ValidateProductHasStock {

	@Override
	public Stock reserve(Stock stock, int quantity, String customerId, String cartId) {
		if (stock.getTotalQuantity() < quantity) {
			throw new IllegalDomainException("Quantity of items greater than the product stocks");
		}

		var stockId = stock.getStockId().getStockId();
		stock.addReservations(List.of(new Reservations(stockId, cartId, customerId, quantity)));
		return stock;
	}
}