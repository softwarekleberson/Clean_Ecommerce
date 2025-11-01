package com.cleancode.ecommerce.customer.domain.customer.repository;

import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.customer.domain.customer.Customer;

public interface CustomerRepository {

	void save (Customer customer);
	Optional<Customer> getCustomerById(String id);
	List<Customer> getAllCustomers();

}
