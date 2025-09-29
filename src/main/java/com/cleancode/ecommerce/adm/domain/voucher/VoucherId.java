package com.cleancode.ecommerce.adm.domain.voucher;

import java.util.Objects;
import java.util.UUID;

public class VoucherId {

	private final String voucherId;

	public VoucherId(String voucherId) {
		this.voucherId = Objects.requireNonNull(voucherId);
	}
	
	public VoucherId () {
		this.voucherId = UUID.randomUUID().toString();
	}
	
	public String getVoucherId() {
		return voucherId;
	}
}
