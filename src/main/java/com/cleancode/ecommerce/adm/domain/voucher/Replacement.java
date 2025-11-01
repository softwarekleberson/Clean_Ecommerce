package com.cleancode.ecommerce.adm.domain.voucher;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

public class Replacement extends Voucher {
	
	private final CustomerId customerId;
	private final Discount discount;
	
	public Replacement(VoucherId id, Message message, LocalDate emission, TypeVoucher typeVoucher,
			CustomerId customerId, Discount discount) {
		super(id, message, emission, typeVoucher);
		this.customerId = customerId;
		this.discount = discount;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public Discount getDiscount() {
		return discount;
	}
}
