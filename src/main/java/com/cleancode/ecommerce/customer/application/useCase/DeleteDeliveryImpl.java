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
	public void execute(String customerId, String deliveryId) {
		Customer customer = repository.getCustomerById(customerId).orElseThrow(()-> new IllegalDomainException("Customer with id : " + customerId + " not found")); 
		customer.removeDelivery(deliveryId);
		
		repository.save(customer);
	}
}