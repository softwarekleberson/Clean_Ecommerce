package com.cleancode.ecommerce.users.domain.voucher;

import java.time.LocalDate;
import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

public class Voucher {

	private final VoucherId voucherId;
	private final CustomerId customerId;
	private final Message message;
	private final LocalDate emission;
	private final TypeVoucher typeVoucher;
	private final Discount discount;
	private boolean active;

	public Voucher(CustomerId customerId, Message message,
			TypeVoucher typeVoucher, Discount discount) {

		this.voucherId = new VoucherId();
		this.customerId = customerId;
		this.message = message;
		this.emission = LocalDate.now();
		this.typeVoucher = typeVoucher;
		this.discount = discount;
		this.active = true;
	}
	
	public Voucher(VoucherId voucherId, CustomerId customerId, Message message, LocalDate emission,
			TypeVoucher typeVoucher, Discount discount, boolean active) {

		this.voucherId = voucherId;
		this.customerId = customerId;
		this.message = message;
		this.emission = emission;
		this.typeVoucher = typeVoucher;
		this.discount = discount;
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}

	public String getVoucherId() {
		return voucherId.getVoucherId();
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public Message getMessage() {
		return message;
	}

	public LocalDate getEmission() {
		return emission;
	}

	public TypeVoucher getTypeVoucher() {
		return typeVoucher;
	}

	public Discount getDiscount() {
		return discount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(voucherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voucher other = (Voucher) obj;
		return Objects.equals(voucherId, other.voucherId);
	}
}
