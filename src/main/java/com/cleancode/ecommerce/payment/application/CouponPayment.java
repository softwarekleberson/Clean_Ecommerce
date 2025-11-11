package com.cleancode.ecommerce.payment.application;

import java.math.BigDecimal;

import com.cleancode.ecommerce.payment.domain.PaymentMethod;
import com.cleancode.ecommerce.users.domain.adm.repository.AdmRepository;

public class CouponPayment implements PaymentMethod {

	private final AdmRepository admRepository;
	
	public CouponPayment( AdmRepository admRepository) {
		this.admRepository = admRepository;
	}
	
	@Override
	public void validate(BigDecimal totalAmount) {

	}
}
