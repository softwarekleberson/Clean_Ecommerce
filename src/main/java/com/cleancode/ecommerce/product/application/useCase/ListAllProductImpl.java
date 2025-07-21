package com.cleancode.ecommerce.product.application.useCase;

import java.util.List;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListAllProductImpl implements ListAllProduct {

	private final ProductRepository repository;

	public ListAllProductImpl(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> getAllProduct() {
		return this.repository.listAllProduct();
	}
}
