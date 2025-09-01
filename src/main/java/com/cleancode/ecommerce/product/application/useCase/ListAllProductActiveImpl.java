package com.cleancode.ecommerce.product.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.dto.output.ProductDtoFactory;
import com.cleancode.ecommerce.product.application.useCase.contract.ListAllProductActive;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListAllProductActiveImpl implements ListAllProductActive {

	private final ProductRepository repository;

	public ListAllProductActiveImpl(ProductRepository repository) {
		this.repository = repository;
	}

	public List<ListProductDto> execute() {
		List<Product> products = this.repository.ListAllProductActive();
		return products.stream()
			.map(ProductDtoFactory::listAllProduct)
            .toList();
	}
}