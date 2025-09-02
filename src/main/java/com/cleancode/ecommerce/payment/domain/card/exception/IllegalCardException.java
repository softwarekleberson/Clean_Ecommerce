package com.cleancode.ecommerce.payment.domain.card.exception;

public class IllegalCardException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalCardException(String message) {
		super(message);
	}
}
