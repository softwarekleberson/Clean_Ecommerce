package com.cleancode.ecommerce.payment.domain;

import java.math.BigDecimal;

public interface PaymentMethod {

	void validate (BigDecimal totalAmount);
}
