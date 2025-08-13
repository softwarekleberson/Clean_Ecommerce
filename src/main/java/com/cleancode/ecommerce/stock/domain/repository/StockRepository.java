package com.cleancode.ecommerce.stock.domain.repository;

import com.cleancode.ecommerce.stock.domain.Stock;

public interface StockRepository {

	Stock save(Stock stoke);
	Stock getStock (String id);
}
