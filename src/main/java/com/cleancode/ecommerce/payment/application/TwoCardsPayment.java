package com.cleancode.ecommerce.payment.application;

import java.math.BigDecimal;

import com.cleancode.ecommerce.payment.domain.PaymentMethod;

public class TwoCardsPayment implements PaymentMethod {

	@Override
	public void validate(BigDecimal totalAmount) {
		// TODO Auto-generated method stub
	}
}
