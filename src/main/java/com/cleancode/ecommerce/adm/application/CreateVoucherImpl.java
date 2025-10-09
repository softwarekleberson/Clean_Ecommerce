package com.cleancode.ecommerce.adm.application;

import com.cleancode.ecommerce.adm.application.contract.CreateVoucher;
import com.cleancode.ecommerce.adm.application.dto.voucher.CreateVoucherDto;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.adm.domain.voucher.repository.VoucherRepository;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateVoucherImpl implements CreateVoucher {

	private final VoucherRepository voucherRepository;
	private final CustomerRepository customerRepository;

	public CreateVoucherImpl(VoucherRepository voucherRepository, CustomerRepository customerRepository) {
		this.voucherRepository = voucherRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void execute(CreateVoucherDto dto) {
		Customer customer = customerRepository.getCustomerById(dto.getCustomerId()).orElseThrow(() -> new IllegalDomainException("Customer not found with id : " + dto.getCustomerId()));
		
		Voucher voucher = dto.execute();
		voucherRepository.save(voucher);
	}
}
