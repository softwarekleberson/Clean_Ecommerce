package com.cleancode.ecommerce.adm.domain.voucher;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Voucher {

	private final VoucherId id;
	private final Message message;
	private final LocalDate emission;
	private final TypeVoucher typeVoucher;

	public Voucher(Message message, TypeVoucher typeVoucher) {
		this(new VoucherId(),
			 Objects.requireNonNull(message),
			 LocalDate.now(),
			 Objects.requireNonNull(typeVoucher)
		);
	}

	public Voucher(VoucherId id, Message message, LocalDate emission, TypeVoucher typeVoucher) {
		this.id = Objects.requireNonNull(id);
		this.message = Objects.requireNonNull(message);
		this.emission = Objects.requireNonNull(emission);
		this.typeVoucher = Objects.requireNonNull(typeVoucher);
	}

	public String getId() {
		return id.getVoucherId();
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
}
