package com.cleancode.ecommerce.payment.domain.card;

import com.cleancode.ecommerce.payment.domain.card.exception.IllegalCardException;

public class Code {

	public static final int CODE_NUMBER = 3;
	private String code;

	public Code(String code) {
		if (code.length() != CODE_NUMBER) {
			throw new IllegalCardException("Code is required in card and needs 3 numbers");

		}
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
