package com.cleancode.ecommerce.stock.application.service;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ProductActivationServiceImpl implements ProductActivationService {

	private final int INVENTORY_CONTROL = 0;
	
	public Product activateProductIfStockAvailable(Product product, Stock stock) {

		if (stock.getTotalQuantity() > INVENTORY_CONTROL) {
			product.activate();
		} else if (stock.getTotalQuantity() <= INVENTORY_CONTROL) {
			product.productStatusPolicyAutomaticDeactivation();
		}
		return product;
	}
}