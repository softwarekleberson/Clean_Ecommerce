package com.cleancode.ecommerce.adm.domain.voucher;

import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;

public class Message {

	private String message;

	public Message(String message) {
		if (message == null || message.isBlank()) {
			throw new IllegalAdmException("Message cannot be null or empty");
		}
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
