package com.cleancode.ecommerce.payment.domain.method;

import java.math.BigDecimal;

import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.payment.domain.entity.PaymentMethod;

public class CouponPayment implements PaymentMethod {

	private final AdmRepository admRepository;
	
	public CouponPayment( AdmRepository admRepository) {
		this.admRepository = admRepository;
	}
	
	@Override
	public void validate(BigDecimal totalAmount) {

	}
}
