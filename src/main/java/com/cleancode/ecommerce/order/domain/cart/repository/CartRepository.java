package com.cleancode.ecommerce.order.domain.cart.repository;

import java.util.Optional;

import com.cleancode.ecommerce.order.domain.cart.Cart;

public interface CartRepository {

	Cart save (Cart cart);
	Optional <Cart> getCartCustomer(String customerId);
}
