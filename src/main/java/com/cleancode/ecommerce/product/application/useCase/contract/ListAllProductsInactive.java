package com.cleancode.ecommerce.product.application.useCase.contract;

import java.util.List;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;

public interface ListAllProductsInactive {

	List<ListProductDto> execute();
}
