package com.cleancode.ecommerce.adm.domain.adm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;
import com.cleancode.ecommerce.adm.domain.voucher.Replacement;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public class Adm extends User {

	private Map<String, Voucher> vouchers = new HashMap<>();

	public Adm(Email email, Password password) {
		super(Objects.requireNonNull(email), Objects.requireNonNull(password));
	}
	
	public Adm(UserId userId, Email email, Password password) {
		super(userId, email, password);
	}

	public void addVoucher(Voucher voucher) {
		this.vouchers.put(voucher.getId(), voucher);
	}

	public Replacement getReplacementById(String id) {
		validateId(id);
		Voucher v = vouchers.get(id);

		if (!(v instanceof Replacement replacement)) {
			throw new IllegalAdmException("Voucher not found or not by Replacement: " + id);
		}

		return replacement;
	}

	public void removeVoucher(String id) {
		validateId(id);

		if (vouchers.remove(id) == null) {
			throw new IllegalAdmException("Voucher not found: " + id);
		}
	}

	private void validateId(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalAdmException("Id do voucher não pode ser nulo ou vazio");
		}
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
