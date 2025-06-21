package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.event.EventNewCustomer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncryptor;
import com.cleancode.ecommerce.customer.shared.domain.event.EventPublisher;

public class CreateCustomer {

	private final CustomerRepository repository;
	private final PasswordEncryptor passwordEncryptor;
	private final EventPublisher eventPublisher;
	
	public CreateCustomer(CustomerRepository repository, PasswordEncryptor passwordEncryptor, EventPublisher eventPublisher) {
		this.repository = repository;
		this.passwordEncryptor = passwordEncryptor;
		this.eventPublisher = eventPublisher;
	}
	
	public void execute(CreateCustomerDto dto) {
		checkEmail(dto.getEmail());
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		String encryptedPassword = passwordEncryptor.encryptPassword(dto.getPassword());
		
		Customer customer = dto.createCustomer(new CustomerId(UUID.randomUUID()), encryptedPassword);
		repository.save(customer);
		
		EventNewCustomer event = new EventNewCustomer(customer.getCpf(), customer.getName());
		eventPublisher.process(event);
	}

	private void checkPassword(String password, String confirmPassword) {
		if(!password.equals(confirmPassword)) throw new IllegalDomainException("password does not match confirm password");
	}

	private void checkEmail(String email) {
		if(repository.emailInUse(email)) throw new IllegalDomainException("email already in use");
	}
}