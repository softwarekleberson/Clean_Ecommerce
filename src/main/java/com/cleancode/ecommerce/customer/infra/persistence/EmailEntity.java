package com.cleancode.ecommerce.customer.infra.persistence;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmailEntity {

	private String email;
	
	public EmailEntity() {
	}

	public EmailEntity(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
