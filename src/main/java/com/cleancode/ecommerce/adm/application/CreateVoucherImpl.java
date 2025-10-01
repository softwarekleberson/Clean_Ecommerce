package com.cleancode.ecommerce.adm.application;

import com.cleancode.ecommerce.adm.application.contract.CreateVoucher;
import com.cleancode.ecommerce.adm.application.dto.voucher.CreateVoucherDto;
import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateVoucherImpl implements CreateVoucher {

	private final AdmRepository repository;
	private final CustomerRepository customerRepository;

	public CreateVoucherImpl(AdmRepository repository, CustomerRepository customerRepository) {
		this.repository = repository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void execute(CreateVoucherDto dto) {
		Adm adm = repository.findAdmByEmail(dto.getEmailAdm()).orElseThrow(() -> new IllegalAdmException("Adm not found"));
		Customer customer = customerRepository.getCustomerById(dto.getCustomerId()).orElseThrow(() -> new IllegalDomainException("Customer not found with id : " + dto.getCustomerId()));
		
		Voucher voucher = dto.execute();
		adm.addVoucher(voucher);
		repository.save(adm);
	}
}
