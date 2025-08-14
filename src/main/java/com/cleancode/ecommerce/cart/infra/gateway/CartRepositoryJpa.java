package com.cleancode.ecommerce.cart.infra.gateway;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cleancode.ecommerce.cart.domain.Cart;
import com.cleancode.ecommerce.cart.domain.repository.CartRepository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartRepositoryJpa implements CartRepository{

	private final CartJpa cartJpa;
	
	public CartRepositoryJpa(CartJpa cartJpa) {
		this.cartJpa = cartJpa;
	}
	
	@Transactional
	@Override
	public Cart save(Cart cart) {
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cart> getCartCustomer(String customerId) {
		return Optional.empty();
	}
}
