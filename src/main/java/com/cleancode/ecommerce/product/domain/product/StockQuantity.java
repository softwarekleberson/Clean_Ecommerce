package com.cleancode.ecommerce.product.domain.product;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class StockQuantity {

	public static final int SMALLEST_AMOUNT = 0;
	private int stockQuantity;
	
	public StockQuantity(int stockQuantity) {
		if(stockQuantity < SMALLEST_AMOUNT) {
			throw new IllegalDomainException("Stock Quantity cannot be less than 0 ");
		}
		
		this.stockQuantity = stockQuantity;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}

	public static StockQuantity updateStockQuantity(int newStockQuantity) {
		return new StockQuantity(newStockQuantity);
	}	
}
