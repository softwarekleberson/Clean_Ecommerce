package com.cleancode.ecommerce.cart.application.service;

import com.cleancode.ecommerce.stock.domain.Stock;

public interface ValidateProductHasStock {

	public Stock reserve (Stock stock, int quantity, String customerId, String cartId);

}
