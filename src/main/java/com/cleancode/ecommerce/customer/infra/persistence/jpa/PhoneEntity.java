package com.cleancode.ecommerce.customer.infra.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneEntity {

	private String ddd;
	private String phone;
	
	@Column(name = "type_phone")
	@Enumerated(EnumType.STRING)
	private TypePhoneEntity typePhone;
	
}
