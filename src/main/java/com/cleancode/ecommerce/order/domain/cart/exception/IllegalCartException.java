package com.cleancode.ecommerce.order.domain.cart.exception;

public class IllegalCartException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalCartException(String message) {
		super(message);
	}
}
