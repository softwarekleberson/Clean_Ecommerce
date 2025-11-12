package com.cleancode.ecommerce.customer.application.useCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	public Page<ListAllCustomersDto> execute(Pageable pageable) {
		Page<Customer> customers = repository.getAllCustomers(pageable);
		if (customers.isEmpty()) {
			throw new IllegalDomainException("No registered customers");
		}

		return customers.map(ListAllCustomersDto::new);
	}
}