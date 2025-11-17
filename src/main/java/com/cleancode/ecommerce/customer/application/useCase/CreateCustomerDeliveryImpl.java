package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCustomerDeliveryImpl implements CreateCustomerDelivery{

	private final CustomerRepository repository;

	public CreateCustomerDeliveryImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public ListCustomerDto execute(String email, CreateDeliveryDto dto) {
		Customer customer = repository.findByEmail(email).orElseThrow(() -> new IllegalDomainException("Customer not found"));				
		Delivery delivery = dto.createDelivery();
		
		customer.registerDelivery(delivery);
		repository.save(customer);
		return new ListCustomerDto(customer);
	}
}
