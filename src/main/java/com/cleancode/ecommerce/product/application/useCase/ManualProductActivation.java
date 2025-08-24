package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.input.ProductStatusChangeDto;

public interface ManualProductActivation {

	void execute(String productId, ProductStatusChangeDto dto);
}
