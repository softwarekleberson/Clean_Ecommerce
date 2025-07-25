package com.cleancode.ecommerce.product.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.dto.output.ProductDtoFactory;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListAllProductImpl implements ListAllProduct {

	private final ProductRepository repository;

	public ListAllProductImpl(ProductRepository repository) {
		this.repository = repository;
	}

	public List<ListProductDto> execute() {
		List<Product> products = this.repository.listAll();
		return products.stream()
			.map(ProductDtoFactory::listAllProduct)
            .toList();
	}
}