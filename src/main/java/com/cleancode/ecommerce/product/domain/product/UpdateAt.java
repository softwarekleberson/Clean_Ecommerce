package com.cleancode.ecommerce.product.domain.product;

import java.time.LocalDateTime;

public class UpdateAt {

	private LocalDateTime updateAt;
	
	public UpdateAt() {
		this.updateAt = LocalDateTime.now();
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
