package com.cleancode.ecommerce.stock.application.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.stock.domain.ProductInput;
import com.cleancode.ecommerce.stock.domain.Stock;

public class ProductPriceServiceImpl implements ProductPriceService {

	@Override
	public Product productPriceService(Product product, Stock stock) {
		BigDecimal pricing = product.getPricing().getPricing();
		BigDecimal totalPurchasePrice = BigDecimal.ZERO;

		List<ProductInput> inputs = stock.getProductInput();
		for (ProductInput input : inputs) {
			totalPurchasePrice = totalPurchasePrice.add(input.getPurchasePrice().getPrice());
		}

		BigDecimal averagePurchasePrice = BigDecimal.ZERO;
		if (!inputs.isEmpty()) {
			averagePurchasePrice = totalPurchasePrice
			.divide(BigDecimal.valueOf(inputs.size()), 2,
			RoundingMode.HALF_UP);
		}

		BigDecimal price = averagePurchasePrice.multiply(BigDecimal.valueOf(1).add(pricing));
		product.productSalesValue(price, product.getPrice().getCoin());

		return product;
	}
}