package com.cleancode.ecommerce.adm.domain.voucher;

import java.util.Objects;

import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;

public class Discount {
	
	private static final double LESS_VALUE_DISCOUNT = 0;
	private final double discount;

	public Discount(double discount) {
		if (discount <= LESS_VALUE_DISCOUNT) {
			throw new IllegalAdmException("Discount must be between 0% and 100%");
		}
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(discount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		return discount == other.discount;
	}
}
