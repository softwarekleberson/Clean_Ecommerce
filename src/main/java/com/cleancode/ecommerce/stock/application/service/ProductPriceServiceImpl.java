package com.cleancode.ecommerce.stock.application.service;

import java.math.BigDecimal;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ProductPriceServiceImpl implements ProductPriceService {

	@Override
	public Product productPriceService(Product product, Stock stock) {
		BigDecimal pricing = product.getPricing().getPricing();

		BigDecimal highestPurchasePrice = stock.getProductInput().stream()
				.map(input -> input.getPurchasePrice().getPrice()).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);

		product.salePriceWithinMarginPolicy(pricing, highestPurchasePrice, product.getPrice().getCoin());

		return product;
	}
}