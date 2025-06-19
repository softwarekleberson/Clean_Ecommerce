package com.cleancode.ecommerce.customer.domain;

import com.cleancode.ecommerce.customer.domain.exception.IllegalContactException;

public class Phone {

	private String ddd;
	private String phone;
	
	public Phone (String ddd, String phone) {
		if(ddd == null || phone == null) {
			throw new IllegalContactException("DDD or Phone invalid");
		}
		
		if(!ddd.matches("\\d{2}")) {
			throw new IllegalContactException("DDD needs 2 digits ");
		}
		
		if(!phone.matches("\\d{9}")) {
			throw new IllegalContactException("Phone needs 9 digits ");
		}
		
		this.ddd = ddd;
		this.phone = phone;
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public String getPhone() {
		return phone;
	}
	
	@Override
	public String toString() {
		return "Phone [ddd=" + ddd + ", phone=" + phone + "]";
	}
}
