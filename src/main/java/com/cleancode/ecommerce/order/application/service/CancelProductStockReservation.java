package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public interface CancelProductStockReservation {

	Stock cancelReservationAfterDelete (Stock stock, String reservationId);
}
