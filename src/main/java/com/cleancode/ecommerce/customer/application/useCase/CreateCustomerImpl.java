package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.NewCustomerEvent;

public class CreateCustomerImpl implements CreateCustomer {

	private final CustomerRepository repository;
	private final EventPublisher eventPublisher;

	public CreateCustomerImpl(CustomerRepository repository, EventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
	}

	public ListCustomerDto execute(CreateCustomerDto dto) {
				
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		Customer customer = dto.createCustomer();
		
		repository.save(customer);
		eventPublisher.publish(new NewCustomerEvent(customer.getName().getName(), customer.getEmail().getEmail()));
		return new ListCustomerDto(customer);
	}

	private void checkPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword))
			throw new IllegalDomainException("password does not match confirm password");
	}
}