package com.cleancode.ecommerce.customer.infra.persistence.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class DatabaseMemory implements CustomerRepository{

	private List<Customer> customer = new ArrayList<>();
 	
	@Override
	public void save(Customer customer) {
		this.customer.add(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(UUID id) {
		return Optional.empty();
	}
}
