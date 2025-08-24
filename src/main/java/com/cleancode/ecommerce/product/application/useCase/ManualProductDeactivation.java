package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.input.ProductStatusChangeDto;

public interface ManualProductDeactivation {

	void execute(String productId, ProductStatusChangeDto dto);
}
