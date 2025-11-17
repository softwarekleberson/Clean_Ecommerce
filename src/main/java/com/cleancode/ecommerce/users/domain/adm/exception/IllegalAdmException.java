package com.cleancode.ecommerce.users.domain.adm.exception;

public class IllegalAdmException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalAdmException(String message) {
		super(message);
	}
}
