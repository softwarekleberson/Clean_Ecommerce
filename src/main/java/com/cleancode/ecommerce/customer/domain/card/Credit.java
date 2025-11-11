package com.cleancode.ecommerce.customer.domain.card;

import java.math.BigDecimal;

public class Credit {

	private final BigDecimal credit;
	
	public Credit() {
		this.credit = BigDecimal.valueOf(100);
	}
	
	public BigDecimal getCredit() {
		return credit;
	}
}
