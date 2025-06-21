package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateChargeDto;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCustomerCharge {

	private final CustomerRepository repository;
	
	public CreateCustomerCharge(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public void execute(UUID id, CreateChargeDto dto) {
		Customer customer = repository.getCustomerById(id).orElseThrow(() -> new IllegalDomainException("Customer with id : " + id + " not found"));
		
		Charge charge = dto.createCharge();
		customer.insertNewCharge(charge);
		repository.save(customer);
	}
}