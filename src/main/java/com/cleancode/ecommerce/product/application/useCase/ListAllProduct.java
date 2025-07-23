package com.cleancode.ecommerce.product.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;

public interface ListAllProduct {

	List<ListProductDto> getAllProduct();
}
