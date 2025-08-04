package com.cleancode.ecommerce.stock.application.usecase;

import com.cleancode.ecommerce.stock.application.dto.CreateStockDto;
import com.cleancode.ecommerce.stock.application.dto.ListStockDto;

public interface CreateStock {

	ListStockDto execute (CreateStockDto dto);
}
