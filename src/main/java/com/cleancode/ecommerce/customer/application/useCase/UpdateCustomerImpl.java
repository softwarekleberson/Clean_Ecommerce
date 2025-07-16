package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdateCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCustomer;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class UpdateCustomerImpl implements UpdateCustomer {

	private final CustomerRepository repository;

	public UpdateCustomerImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public ListCustomerDto updateCustomer(String customerId, UpdateCustomerDto dto) {
		Customer customer = repository.getCustomerById(customerId)
				.orElseThrow(() -> new IllegalDomainException("Customer with id : " + customerId + " not found"));
				
		customer.updateCustomer(dto.name(), dto.birth(), dto.ddd(), dto.phone(), dto.typePhone());
		repository.save(customer);
		return new ListCustomerDto(customer);
	}
}