package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.CreateProductDto;

public interface CreateProduct {

	void execute(CreateProductDto dto);

}
