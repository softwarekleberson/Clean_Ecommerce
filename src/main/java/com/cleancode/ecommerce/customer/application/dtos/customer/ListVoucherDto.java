package com.cleancode.ecommerce.customer.application.dtos.customer;

import java.time.LocalDate;

import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;

public record ListVoucherDto(String voucherId, String customerId, String message, LocalDate emission,
							 TypeVoucher typeVoucher, double discount) {

	public ListVoucherDto(Voucher voucher) {
		this(voucher.getVoucherId(), voucher.getCustomerId().getValue(), voucher.getMessage().getMessage(), voucher.getEmission(), voucher.getTypeVoucher(), voucher.getDiscount().getDiscount());
	}

}
