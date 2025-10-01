package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public interface UpdateNewReservation {

	Stock creteNewReservation (Stock stock, int quantity, String customerId, String cartId);
}
