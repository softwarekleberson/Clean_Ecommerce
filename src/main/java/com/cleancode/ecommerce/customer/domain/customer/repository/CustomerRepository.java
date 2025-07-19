package com.cleancode.ecommerce.customer.domain.customer.repository;

import java.util.Optional;

import com.cleancode.ecommerce.customer.domain.customer.Customer;

public interface CustomerRepository {

	void save (Customer customer);
	Optional<Customer> getCustomerById(String id);
}
