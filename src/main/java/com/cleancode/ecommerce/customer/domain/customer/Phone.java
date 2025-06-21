package com.cleancode.ecommerce.customer.domain.customer;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;

public class Phone {

	private String ddd;
	private String phone;
	private TypePhone typePhone;
	
	public Phone (String ddd, String phone, TypePhone typePhone) {
		if(ddd == null || ddd.trim().isEmpty()) {
			throw new IllegalContactException("Ddd needs 2 digits ");
		}
		
		if(phone == null || phone.trim().isEmpty()) {
			throw new IllegalContactException("Phone needs 9 digits ");
		}
		
		if(!ddd.matches("\\d{2}") || !phone.matches("\\d{9}")) {
			throw new IllegalContactException("DDD needs 2 digits and Phone needs 9 digits ");
		}
		
		this.ddd = ddd;
		this.phone = phone;
		this.typePhone = typePhone;
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public TypePhone getTypePhone() {
		return typePhone;
	}
	
	@Override
	public String toString() {
		return "Phone [ddd=" + ddd + ", phone=" + phone + "]";
	}
}
