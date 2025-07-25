package com.cleancode.ecommerce.product.domain;

import java.time.LocalDateTime;

public class UpdateAt {

	private final LocalDateTime updateAt;
	
	public UpdateAt() {
		this.updateAt = LocalDateTime.now();
	}
	
	public UpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public static UpdateAt update() {
		return new UpdateAt();
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
