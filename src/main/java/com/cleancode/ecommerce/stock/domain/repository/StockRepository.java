package com.cleancode.ecommerce.stock.domain.repository;

import com.cleancode.ecommerce.stock.domain.Stock;

public interface StockRepository {

	Stock create(Stock stoke);
	Stock getStock (String id);
}
