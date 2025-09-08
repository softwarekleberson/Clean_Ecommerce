package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.order.application.dtos.input.DeleteUniqueProductToCartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteUniqueProductCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;

public class DeleteUniqueProductCartImpl implements DeleteUniqueProductCart {

	private final CartRepository cartRepository;

	public DeleteUniqueProductCartImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public void execute(String customerId, DeleteUniqueProductToCartDto dto) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));
		
		cart.removeProductFromCart(new CartItemId(dto.cartItemId()));
		cartRepository.save(cart);
	}
}