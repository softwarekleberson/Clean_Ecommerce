package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListProductImpl implements ListProduct {

	private final ProductRepository repository;

	public ListProductImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Product execute(String idProduct) {
		return repository.listProduct(idProduct);
	}
}