package com.cleancode.ecommerce.customer.application.useCase;

import java.util.Collections;
import java.util.List;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListVoucherDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListVoucherCustomer;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.users.domain.voucher.Voucher;
import com.cleancode.ecommerce.users.domain.voucher.repository.VoucherRepository;

public class ListVoucherCustomerImpl implements ListVoucherCustomer {

	private final VoucherRepository repository;
	private final CustomerRepository customerRepository;

	public ListVoucherCustomerImpl(VoucherRepository repository, CustomerRepository customerRepository) {
		this.repository = repository;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<ListVoucherDto> execute(String email) {
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalDomainException("Customer not found"));
		
		List<Voucher> vouchers = repository.listAllVouche(customer.getId().getValue());
		
	    if (vouchers == null || vouchers.isEmpty()) {
	        return Collections.emptyList();
	    }

	    return vouchers.stream()
	                   .map(ListVoucherDto::new)
	                   .toList();
	}	
}
