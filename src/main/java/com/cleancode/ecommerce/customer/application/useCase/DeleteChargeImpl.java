package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteCharge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class DeleteChargeImpl implements DeleteCharge{

	private final CustomerRepository repository;

	public DeleteChargeImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(String email, String ChargeId) {
		Customer customer = repository.findByEmail(email).orElseThrow(()-> new IllegalDomainException("Customer not found")); 
		customer.removeChargeById(ChargeId);
		
		repository.save(customer);
	}
}