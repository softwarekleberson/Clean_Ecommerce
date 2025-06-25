package com.cleancode.ecommerce.customer.infra.persistence;

import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneEntity {

	private String ddd;
	private String phone;
	private TypePhoneEntity typePhone;
	
	public PhoneEntity() {
	}

	public PhoneEntity(String ddd, String phone, TypePhoneEntity typePhone) {
		this.ddd = ddd;
		this.phone = phone;
		this.typePhone = typePhone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public TypePhoneEntity getTypePhone() {
		return typePhone;
	}

	public void setTypePhone(TypePhoneEntity typePhone) {
		this.typePhone = typePhone;
	}
}
