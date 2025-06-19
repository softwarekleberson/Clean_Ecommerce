package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.domain.Customer;
import com.cleancode.ecommerce.customer.domain.CustomerId;
import com.cleancode.ecommerce.customer.domain.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.repository.PasswordEncryptor;

public class CreateCustomer {

	private final CustomerRepository repository;
	private final PasswordEncryptor passwordEncryptor;
	
	public CreateCustomer(CustomerRepository repository, PasswordEncryptor passwordEncryptor) {
		this.repository = repository;
		this.passwordEncryptor = passwordEncryptor;
	}
	
	public void execute(CreateCustomerDto dto) {
		checkEmail(dto.getEmail());
		checkPassword(dto.getPassword(), dto.getConfirmPassword());
		String encryptedPassword = passwordEncryptor.encryptPassword(dto.getPassword());
		
		Customer customer = dto.createCustomer(new CustomerId(UUID.randomUUID()), encryptedPassword);
		repository.save(customer);
	}

	private void checkPassword(String password, String confirmPassword) {
		if(!password.equals(confirmPassword)) throw new IllegalDomainException("password does not match confirm password");
	}

	private void checkEmail(String email) {
		if(repository.emailInUse(email)) throw new IllegalDomainException("email already in use");
	}
}