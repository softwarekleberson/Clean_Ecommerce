package com.cleancode.ecommerce.stock.application.dto;

import com.cleancode.ecommerce.product.domain.IdProduct;
import com.cleancode.ecommerce.stock.domain.Stock;

public class CreateStockDto {

	private String productId;
	
	public String getProductId() {
		return productId;
	}
	
	public Stock createStock() {
		return new Stock(new IdProduct(productId));
	}
}