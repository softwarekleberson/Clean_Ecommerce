package com.cleancode.ecommerce.stock.application.usecase;

import com.cleancode.ecommerce.stock.application.dto.CreateInputStockDto;
import com.cleancode.ecommerce.stock.application.dto.ListStockDto;

public interface CreateProductInput {

	ListStockDto execute (CreateInputStockDto dto);
}
