package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.ChangeActivationStatusAdm;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class ChangeActivationStatusAdmImpl implements ChangeActivationStatusAdm {

	private final CustomerRepository repository;

	public ChangeActivationStatusAdmImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String customerId) {
		Customer customer = repository.getCustomerById(customerId)
				.orElseThrow(() -> new IllegalDomainException("Customer with id : " + customerId + " not found"));

		customer.changeActivationStatusByAdmin();
		repository.save(customer);
	}
}