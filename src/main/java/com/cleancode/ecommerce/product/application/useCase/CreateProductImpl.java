package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.application.dto.CreateProductDto;
import com.cleancode.ecommerce.product.domain.product.Product;
import com.cleancode.ecommerce.product.domain.product.repository.ProductRepository;

public class CreateProductImpl implements CreateProduct{

	private final ProductRepository repository;
	
	public CreateProductImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	public void execute(CreateProductDto dto) {
		Product product = dto.toProduct();
		repository.create(product);
	}
}
