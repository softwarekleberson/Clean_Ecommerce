package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.order.application.useCase.contract.DeleteAllCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;

public class DeleteAllCartImpl implements DeleteAllCart {

	private final CartRepository cartRepository;

	public DeleteAllCartImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public void execute(String customerId) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));

		cart.removeAllProducts();
		cartRepository.save(cart);
	}
}