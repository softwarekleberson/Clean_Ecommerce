package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCustomerDeliveryImpl implements CreateCustomerDelivery{

	private final CustomerRepository repository;

	public CreateCustomerDeliveryImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public void execute(UUID id, CreateDeliveryDto dto) {
		Customer customer = repository.getCustomerById(id).orElseThrow(() -> new IllegalDomainException("Customer with id : " + id + " not found"));
		Delivery delivery = dto.createDelivery();
		
		customer.insertNewDelivery(delivery);
		repository.save(customer);
	}
}
