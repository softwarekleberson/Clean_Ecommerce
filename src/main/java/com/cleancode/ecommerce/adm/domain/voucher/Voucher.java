package com.cleancode.ecommerce.adm.domain.voucher;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Voucher {

	private final VoucherId id;
	private Message message;
	private final LocalDate emission;
	private final TypeVoucher typeVoucher;
	
	public Voucher(Message message, TypeVoucher typeVoucher) {
		this.id = new VoucherId(UUID.randomUUID().toString());
		this.message = message;
		this.emission = LocalDate.now();
		this.typeVoucher = typeVoucher;
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
		return Objects.hash(emission, id, message, typeVoucher);
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
		return Objects.equals(emission, other.emission) && Objects.equals(id, other.id)
				&& Objects.equals(message, other.message) && typeVoucher == other.typeVoucher;
	}
}
