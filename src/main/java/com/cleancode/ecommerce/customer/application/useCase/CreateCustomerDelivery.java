package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.domain.Customer;
import com.cleancode.ecommerce.customer.domain.Delivery;
import com.cleancode.ecommerce.customer.domain.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.repository.CustomerRepository;

public class CreateCustomerDelivery {

	private final CustomerRepository repository;

	public CreateCustomerDelivery(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public void execute(UUID id, CreateDeliveryDto dto) {
		Customer customer = repository.getCustomerById(id).orElseThrow(() -> new IllegalDomainException("Customer with id : " + id + " not found"));
		Delivery delivery = dto.createDelivery();
		
		customer.insertNewDelivery(delivery);
		repository.save(customer);
	}
}
