package com.cleancode.ecommerce.customer.infra.persistence.jpa.customer;

import java.util.Optional;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.infra.gateways.CustomerJpa;
import com.cleancode.ecommerce.customer.infra.mapper.CustomerMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

	private final CustomerJpa jpa;

	public CustomerRepositoryJpa(CustomerJpa jpa) {
		this.jpa = jpa;
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		Optional<CustomerEntity> optionalEntity = jpa.findFullById(customer.getId().getValue().toString());

		CustomerEntity entity;

		if (optionalEntity.isPresent()) {
			entity = CustomerMapper.toEntity(customer, optionalEntity.get());
		} else {
			entity = CustomerMapper.toEntity(customer);
		}

		jpa.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> getCustomerById(String id) {
		return jpa.findFullById(id).map(CustomerMapper::toDomain);
	}
}