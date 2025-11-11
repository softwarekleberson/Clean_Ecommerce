package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.ListCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;

public class ListCartImpl implements ListCart {

	private final CartRepository cartRepository;
	private final CustomerRepository customerRepository;

	public ListCartImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
		this.cartRepository = cartRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public CartDto execute(String email) {
		Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new IllegalDomainException("Customer not found"));
		
		Cart cart = cartRepository.getCartCustomer(customer.getId().getValue())
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customer.getId().getValue() + " not found"));

		return new CartDto(cart);
	}
}