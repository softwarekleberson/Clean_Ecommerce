package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListCustomer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class ListCustomerImpl implements ListCustomer {

	private final CustomerRepository repository;

	public ListCustomerImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ListCustomerDto execute(String email) {
		return new ListCustomerDto(repository.findByEmail(email).orElseThrow(()-> new IllegalDomainException("Customer with not found"))); 
	}
}