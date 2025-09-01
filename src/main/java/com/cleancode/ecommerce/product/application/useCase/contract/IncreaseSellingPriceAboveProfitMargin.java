package com.cleancode.ecommerce.product.application.useCase.contract;

import com.cleancode.ecommerce.product.application.dto.input.ModifySellingPriceDto;

public interface IncreaseSellingPriceAboveProfitMargin {

	public void execute(String productId, ModifySellingPriceDto dto);
}
