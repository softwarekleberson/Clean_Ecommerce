package com.cleancode.ecommerce.users.domain.voucher.repository;

import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.users.domain.voucher.Voucher;

public interface VoucherRepository {

	void save (Voucher voucher);
	List <Voucher> listAllVouche (String id);
	Optional<Voucher> listSingleVoucher(String voucherId);
}
