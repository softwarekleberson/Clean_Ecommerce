package com.cleancode.ecommerce.users.domain.voucher;

import java.util.Objects;

public class Message {

	private String message;

	public Message(String message) {
		this.message = Objects.requireNonNull(message);
	}

	public String getMessage() {
		return message;
	}
}
