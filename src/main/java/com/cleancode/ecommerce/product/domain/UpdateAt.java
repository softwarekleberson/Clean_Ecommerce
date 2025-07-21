package com.cleancode.ecommerce.product.domain;

import java.time.LocalDateTime;

public class UpdateAt {

	private LocalDateTime updateAt;
	
	public UpdateAt() {
		this.updateAt = LocalDateTime.now();
	}
	
	public static UpdateAt update() {
		return new UpdateAt();
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
