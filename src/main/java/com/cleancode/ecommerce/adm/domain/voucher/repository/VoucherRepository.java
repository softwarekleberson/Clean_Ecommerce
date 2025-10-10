package com.cleancode.ecommerce.adm.domain.voucher.repository;

import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.adm.domain.voucher.Voucher;

public interface VoucherRepository {

	void save (Voucher voucher);
	List <Voucher> listAllVoucher (String customerId);
	Optional<Voucher> listSingleVoucher(String voucherId);
}
