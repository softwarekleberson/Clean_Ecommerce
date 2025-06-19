package com.cleancode.ecommerce.customer.domain.exception;

public class IllegalDomainException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalDomainException(String message) {
		super(message);
	}
}
