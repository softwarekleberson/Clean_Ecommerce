package com.cleancode.ecommerce.adm.domain.voucher;

import java.time.LocalDate;

public class Promotional extends Voucher {

	private static final int VALIDITY_DAY = 2;
	
	private CodeVoucher codeVoucher;
	private Discount discount;
	private LocalDate validity;

	public Promotional(Message message, CodeVoucher codeVoucher,
			           Discount discount) {
		super(message, TypeVoucher.PROMOTIONAL);
		this.codeVoucher = codeVoucher;
		this.discount = discount;
		this.validity = LocalDate.now().plusDays(VALIDITY_DAY);
	}

	public String getCodeVoucher() {
		return codeVoucher.getCode();
	}

	public Discount getDiscount() {
		return discount;
	}

	public LocalDate getValidity() {
		return validity;
	}
}
