package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.CreateProductDto;
import com.cleancode.ecommerce.product.domain.product.Product;
import com.cleancode.ecommerce.product.domain.product.repository.ProductRepository;

public class CreateProduct {

	private final ProductRepository repository;
	
	public CreateProduct(ProductRepository repository) {
		this.repository = repository;
	}
	
	public void create(CreateProductDto dto) {
		Product product = dto.toProduct();
		repository.create(product);
	}
}
