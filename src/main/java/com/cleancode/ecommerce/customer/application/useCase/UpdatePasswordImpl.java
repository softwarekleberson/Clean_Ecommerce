package com.cleancode.ecommerce.customer.application.useCase;

import java.util.Objects;

import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdatePassword;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class UpdatePasswordImpl implements UpdatePassword {

	private final CustomerRepository repository;

	public UpdatePasswordImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void updatePassword(String customerId, UpdatePasswordDto dto) {
		Customer customer = repository.getCustomerById(customerId)
				.orElseThrow(() -> new IllegalDomainException("Customer with id : " + customerId + " not found"));

		if(!Objects.equals(dto.password(), dto.confirmPassword())) {
			throw new IllegalDomainException("password confirmation is different from password");
		}
		
		customer.updateCustomer(dto.password());
		repository.save(customer);
	}
}