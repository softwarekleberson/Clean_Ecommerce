package com.cleancode.ecommerce.customer.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListAllCustomersDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListAllCustomers;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class ListAllCustomersImpl implements ListAllCustomers {

	private final CustomerRepository repository;

	public ListAllCustomersImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ListAllCustomersDto> execute() {
		List<Customer> customers = repository.getAllCustomers();
		if (customers.isEmpty()) {
			throw new IllegalDomainException("No registered customers");
		}

		return customers.stream().map(ListAllCustomersDto::new) 
				.toList(); 
	}
}