package com.cleancode.ecommerce.product.domain;

import java.time.LocalDateTime;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class UpdateAt {

	private LocalDateTime updateAt;
	
	public UpdateAt(LocalDateTime updateAt) {
		if(updateAt == null) {
			throw new IllegalDomainException("Update At not be null");
		}
		this.updateAt = updateAt;
	}
	
	public static UpdateAt update() {
		return new UpdateAt(LocalDateTime.now());
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
