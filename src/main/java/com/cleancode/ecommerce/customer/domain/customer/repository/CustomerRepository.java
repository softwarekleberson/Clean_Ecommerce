package com.cleancode.ecommerce.customer.domain.customer.repository;

import java.util.Optional;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Customer;

public interface CustomerRepository {

	void save (Customer customer);
	boolean emailInUse (String email);
	Optional<Customer> getCustomerById(UUID id);
}
