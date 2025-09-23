package com.cleancode.ecommerce.product.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.dto.output.ProductDtoFactory;
import com.cleancode.ecommerce.product.application.useCase.contract.ListAllProductsInactive;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListAllProductsInactiveImpl implements ListAllProductsInactive {

	private final ProductRepository repository;

	public ListAllProductsInactiveImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<ListProductDto> execute() {
		List<Product> products = this.repository.ListAllProductNotActive();
		return products.stream()
			.map(ProductDtoFactory::listAllProduct)
            .toList();
	}
}
