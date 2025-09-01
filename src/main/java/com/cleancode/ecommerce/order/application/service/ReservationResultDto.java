package com.cleancode.ecommerce.order.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public record ReservationResultDto(Stock stock, String reservationId) {

}
