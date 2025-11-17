package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCustomerChargeImpl implements CreateCustomerCharge{

	private final CustomerRepository repository;
	
	public CreateCustomerChargeImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public ListCustomerDto execute(String email, CreateChargeDto dto) {
		Customer customer = repository.findByEmail(email).orElseThrow(() -> new IllegalDomainException("Customer not found"));
		Charge charge = dto.createCharge();
		
		customer.registerCharge(charge);
		repository.save(customer);
		return new ListCustomerDto(customer);
	}
}