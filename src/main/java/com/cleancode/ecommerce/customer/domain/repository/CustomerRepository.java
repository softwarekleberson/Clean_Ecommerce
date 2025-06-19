package com.cleancode.ecommerce.customer.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.Customer;

public interface CustomerRepository {

	void save (Customer customer);
	boolean emailInUse (String email);
	Optional<Customer> getCustomerById(UUID id);
}
