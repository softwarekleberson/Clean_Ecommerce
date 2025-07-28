package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;

public interface ListProduct {

	ListProductDto execute(String idProduct);
}
