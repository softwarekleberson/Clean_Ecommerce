package com.cleancode.ecommerce.customer.application.useCase;

import java.util.Collections;
import java.util.List;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListVoucherDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListVoucherCustomer;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.adm.domain.voucher.repository.VoucherRepository;

public class ListVoucherCustomerImpl implements ListVoucherCustomer {

	private final VoucherRepository repository;

	public ListVoucherCustomerImpl(VoucherRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ListVoucherDto> execute(String customerId) {
		List<Voucher> vouchers = repository.listAllVoucher(customerId);

	    if (vouchers == null || vouchers.isEmpty()) {
	        return Collections.emptyList();
	    }

	    return vouchers.stream()
	                   .map(ListVoucherDto::new)
	                   .toList();
	}	
}
