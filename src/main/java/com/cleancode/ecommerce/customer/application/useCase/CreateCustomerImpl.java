package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.customer.application.useCase.contract.PasswordValidationCheck;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncoderService;
import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.NewCustomerEvent;

public class CreateCustomerImpl implements CreateCustomer {

	private final CustomerRepository repository;
	private final PasswordValidationCheck passwordValidation;
	private final EventPublisher eventPublisher;
    private final EncryptPassword encryptPassword;

	public CreateCustomerImpl(CustomerRepository repository, PasswordValidationCheck passwordValidation,
			EventPublisher eventPublisher, EncryptPassword encryptPassword) {
		this.repository = repository;
		this.passwordValidation = passwordValidation;
		this.eventPublisher = eventPublisher;
		this.encryptPassword = encryptPassword;
	}

	public ListCustomerDto execute(CreateCustomerDto dto) {
		
		passwordValidation.passwordCheckAndConfirmPassword(dto.getPassword(), dto.getConfirmPassword());
		passwordValidation.validateAcceptablePasswordFormat(dto.getPassword());
		
		Customer customer = dto.createCustomer();
		encryptPassword.execute(customer);
		
		repository.save(customer);
		eventPublisher.publish(new NewCustomerEvent(customer.getName().getName(), customer.getEmail().getEmail()));
		return new ListCustomerDto(customer);
	}
}