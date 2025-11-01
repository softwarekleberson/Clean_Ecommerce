package com.cleancode.ecommerce.customer.infra.gateways;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.infra.mapper.CustomerMapper;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

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

	@Override
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return jpa.findAllCustomer()
		.stream()
		.map(CustomerMapper::toDomain)
		.collect(Collectors.toList());
	}
}