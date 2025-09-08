package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.order.application.dtos.input.UpdateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.UpdateCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.stock.domain.Quantity;

public class UpdateCartImpl implements UpdateCart {

	private final CartRepository cartRepository;

	public UpdateCartImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public CartDto execute(String customerId, UpdateCartDto dto) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));

		cart.changeProductQuantity(new CartItemId(dto.cartItemId()), new Quantity(dto.quantity()));		
		
		cartRepository.save(cart);
		return new CartDto(cart);
	}
}