package com.cleancode.ecommerce.adm.domain.voucher;

import java.time.LocalDate;
import java.util.Objects;

public class Promotional extends Voucher {

	private static final int VALIDITY_DAY = 2;
	
	private final CodeVoucher codeVoucher;
	private final Discount discount;
	private final LocalDate validity;

	public Promotional(Message message, CodeVoucher codeVoucher,
			           Discount discount) {
		super(Objects.requireNonNull(message), TypeVoucher.PROMOTIONAL);
		this.codeVoucher = Objects.requireNonNull(codeVoucher);
		this.discount = Objects.requireNonNull(discount);
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

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
