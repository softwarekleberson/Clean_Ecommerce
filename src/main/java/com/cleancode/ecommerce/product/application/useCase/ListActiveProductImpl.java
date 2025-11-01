package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.dto.output.ProductDtoFactory;
import com.cleancode.ecommerce.product.application.useCase.contract.ListActiveProduct;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ListActiveProductImpl implements ListActiveProduct {

	private final ProductRepository repository;

	public ListActiveProductImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public ListProductDto execute(String idProduct) {
		Product product = repository.findById(idProduct)
	   .orElseThrow(() -> new IllegalDomainException("Product with id : " + idProduct + "not found"));
		
		return ProductDtoFactory.listAllProduct(product);
	}
}