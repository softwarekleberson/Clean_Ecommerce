package com.cleancode.ecommerce.cart.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public record ReservationResult(Stock stock, String reservationId) {

}
