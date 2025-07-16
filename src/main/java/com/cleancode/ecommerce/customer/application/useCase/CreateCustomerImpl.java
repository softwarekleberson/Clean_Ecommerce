package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.event.EventNewCustomer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.domain.customer.event.EventPublisher;

public class CreateCustomerImpl implements CreateCustomer {

	private final CustomerRepository repository;
	private final EventPublisher eventPublisher;

	public CreateCustomerImpl(CustomerRepository repository, EventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
	}

	public void execute(CreateCustomerDto dto) {
				
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		Customer customer = dto.createCustomer();
		repository.save(customer);

		EventNewCustomer event = new EventNewCustomer(customer.getCpf(), customer.getName());
		eventPublisher.process(event);
	}

	private void checkPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword))
			throw new IllegalDomainException("password does not match confirm password");
	}
}