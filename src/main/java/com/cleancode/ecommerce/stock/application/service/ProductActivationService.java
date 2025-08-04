package com.cleancode.ecommerce.stock.application.service;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.stock.domain.Stock;

public interface ProductActivationService {

	public Product activateProductIfStockAvailable (Product product, Stock stock);
}
