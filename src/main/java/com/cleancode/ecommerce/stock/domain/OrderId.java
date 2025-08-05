package com.cleancode.ecommerce.stock.domain;

import java.util.UUID;

public class OrderId {

	private final String orderId;

	public OrderId() {
		this.orderId = UUID.randomUUID().toString();
	}

	public OrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}
}