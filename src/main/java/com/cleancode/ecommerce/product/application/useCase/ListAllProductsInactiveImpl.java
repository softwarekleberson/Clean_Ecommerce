package com.cleancode.ecommerce.product.application.useCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	public Page<ListProductDto> execute(Pageable pageable) {
		Page<Product> products = repository.listAllProductNotActive(pageable);
		return products.map(ProductDtoFactory::listAllProduct);
	}
}