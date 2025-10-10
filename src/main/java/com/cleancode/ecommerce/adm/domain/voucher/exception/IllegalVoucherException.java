package com.cleancode.ecommerce.adm.domain.voucher.exception;

public class IllegalVoucherException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalVoucherException(String message) {
		super(message);
	}
}
