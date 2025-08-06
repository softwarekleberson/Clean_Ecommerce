package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCustomerImpl implements CreateCustomer {

	private final CustomerRepository repository;

	public CreateCustomerImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	public ListCustomerDto execute(CreateCustomerDto dto) {
				
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		Customer customer = dto.createCustomer();
		repository.save(customer);

		return new ListCustomerDto(customer);
	}

	private void checkPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword))
			throw new IllegalDomainException("password does not match confirm password");
	}
}