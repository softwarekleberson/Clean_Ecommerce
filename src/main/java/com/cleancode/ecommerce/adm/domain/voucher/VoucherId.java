package com.cleancode.ecommerce.adm.domain.voucher;

import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;

public class VoucherId {

	private final String voucherId;

	public VoucherId(String voucherId) {
		if(voucherId == null ) throw new IllegalAdmException("Voucher need to be present");
		this.voucherId = voucherId;
	}
	
	public String getVoucherId() {
		return voucherId;
	}
}
