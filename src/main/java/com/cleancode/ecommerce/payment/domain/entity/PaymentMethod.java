package com.cleancode.ecommerce.payment.domain.entity;

import java.math.BigDecimal;

public interface PaymentMethod {

	void validate (BigDecimal totalAmount);
}
