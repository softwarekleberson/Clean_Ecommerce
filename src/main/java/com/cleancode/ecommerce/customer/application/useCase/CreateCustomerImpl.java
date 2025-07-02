package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.event.EventNewCustomer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncryptor;
import com.cleancode.ecommerce.shared.domain.customer.event.EventPublisher;

public class CreateCustomerImpl implements CreateCustomer{

	private final CustomerRepository repository;
	private final PasswordEncryptor passwordEncryptor;
	private final EventPublisher eventPublisher;

	public CreateCustomerImpl(CustomerRepository repository, PasswordEncryptor passwordEncryptor,
			EventPublisher eventPublisher) {
		this.repository = repository;
		this.passwordEncryptor = passwordEncryptor;
		this.eventPublisher = eventPublisher;
	}

	public void execute(CreateCustomerDto dto) {
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		String encryptedPassword = passwordEncryptor.encryptPassword(dto.getPassword());

		Customer customer = dto.createCustomer(new Id(UUID.randomUUID()), encryptedPassword);
		repository.save(customer);

		EventNewCustomer event = new EventNewCustomer(customer.getCpf(), customer.getName());
		eventPublisher.process(event);
	}

	private void checkPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword))
			throw new IllegalDomainException("password does not match confirm password");
	}
}