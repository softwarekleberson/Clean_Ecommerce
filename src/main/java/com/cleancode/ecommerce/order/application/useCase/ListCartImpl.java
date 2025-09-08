package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.ListCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;

public class ListCartImpl implements ListCart {

	private final CartRepository cartRepository;

	public ListCartImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public CartDto execute(String customerId) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));

		return new CartDto(cart);
	}
}