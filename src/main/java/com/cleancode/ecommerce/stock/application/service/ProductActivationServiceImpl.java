package com.cleancode.ecommerce.stock.application.service;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ProductActivationServiceImpl implements ProductActivationService {

	public Product activateProductIfStockAvailable(Product product, Stock stock) {

		if (stock.getTotalQuantity() > 0) {
			product.activate();
		} else {
			product.deactivate();
		}
		return product;
	}
}