package com.cleancode.ecommerce.customer.infra.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CustomerRepositoryJpa implements CustomerRepository{

	@Override
	public void save(Customer customer) {
		
	}

	@Override
	public boolean emailInUse(String email) {
		return false;
	}

	@Override
	public Optional<Customer> getCustomerById(UUID id) {
		return Optional.empty();
	}

}
