package com.cleancode.ecommerce.users.domain.voucher.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cleancode.ecommerce.users.domain.voucher.Voucher;

public interface VoucherRepository {

	void save (Voucher voucher);
	public Page<Voucher> listAllVouche(String id, Pageable pageable);
	Optional<Voucher> listSingleVoucher(String voucherId);
}
