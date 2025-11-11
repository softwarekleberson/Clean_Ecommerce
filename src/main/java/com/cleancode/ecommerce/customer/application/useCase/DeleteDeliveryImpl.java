package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteDelivery;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class DeleteDeliveryImpl implements DeleteDelivery {

	private final CustomerRepository repository;

	public DeleteDeliveryImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String email, String deliveryId) {
		Customer customer = repository.findByEmail(email).orElseThrow(()-> new IllegalDomainException("Customer not found")); 
		customer.removeDeliveryById(deliveryId);
		
		repository.save(customer);
	}
}