package com.cleancode.ecommerce.adm.domain.adm;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;
import com.cleancode.ecommerce.adm.domain.voucher.Promotional;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public class Adm extends User {

	private Map<String, Voucher> vouchers = new HashMap<>();

	public Adm(Email email, Password password) {
		super(Objects.requireNonNull(email), Objects.requireNonNull(password));
	}

	public void addVoucher(Voucher voucher) {
		this.vouchers.put(voucher.getId(), voucher);
	}

	public Promotional getVoucherById(String id, String code) {
		Promotional voucher = (Promotional) this.vouchers.get(id);

		if (voucher == null) {
			throw new IllegalAdmException("Voucher with id " + id + " not found");
		}

		if (LocalDate.now().isAfter(voucher.getValidity())) {
			throw new IllegalAdmException("The voucher has expired");
		}

		if (!voucher.getCodeVoucher().equals(code)) {
			throw new IllegalAdmException("The code " + code + " entered is not valid");
		}

		return voucher;
	}

	public void removeVoucher(String id) {
		if (!this.vouchers.containsKey(id)) {
			throw new IllegalAdmException("Voucher not found");
		}
		this.vouchers.remove(id);
	}

	public Map<String, Voucher> getAllVouchers() {
		return Map.copyOf(vouchers);
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
