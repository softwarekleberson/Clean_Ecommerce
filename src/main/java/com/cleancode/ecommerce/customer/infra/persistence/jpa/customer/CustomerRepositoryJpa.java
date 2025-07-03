package com.cleancode.ecommerce.customer.infra.persistence.jpa.customer;

import java.util.Optional;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.infra.gateways.CustomerJpa;
import com.cleancode.ecommerce.customer.infra.gateways.CustomerMapper;

public class CustomerRepositoryJpa implements CustomerRepository {

	private final CustomerMapper mapper;
	private final CustomerJpa jpa;

	public CustomerRepositoryJpa(CustomerMapper mapper, CustomerJpa jpa) {
		this.mapper = mapper;
		this.jpa = jpa;
	}

	@Override
	public void save(Customer customer) {
		CustomerEntity entity = mapper.toEntity(customer);
		jpa.save(entity);
	}

	@Override
	public Optional<Customer> getCustomerById(UUID id) {
		return Optional.empty();
	}
}
