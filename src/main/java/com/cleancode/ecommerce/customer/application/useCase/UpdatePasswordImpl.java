package com.cleancode.ecommerce.customer.application.useCase;

import java.util.Objects;

import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdatePassword;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class UpdatePasswordImpl implements UpdatePassword {

	private final CustomerRepository repository;
    private final EncryptPassword encryptPassword;

	public UpdatePasswordImpl(CustomerRepository repository, EncryptPassword encryptPassword) {
		this.repository = repository;
		this.encryptPassword = encryptPassword;
	}

	@Override
	public void execute(String email, UpdatePasswordDto dto) {
		Customer customer = repository.findByEmail(email)
				.orElseThrow(() -> new IllegalDomainException("Customer not found"));

		if(!Objects.equals(dto.password(), dto.confirmPassword())) {
			throw new IllegalDomainException("password confirmation is different from password");
		}
		
		String passwordEncode = encryptPassword.execute(dto.password());
		customer.updatePassword(passwordEncode);
		repository.save(customer);
	}
}