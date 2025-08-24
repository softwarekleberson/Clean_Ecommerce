package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.application.dto.input.ModifySellingPriceDto;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class IncreaseSellingPriceAboveProfitMarginImpl implements IncreaseSellingPriceAboveProfitMargin {

	private final ProductRepository repository;

	public IncreaseSellingPriceAboveProfitMarginImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String productId, ModifySellingPriceDto dto) {
		Product product = repository.listProduct(productId)
				.orElseThrow(() -> new IllegalDomainException("Product with id : " + productId + "not found"));
		
		product.aboveMarginSalesPricePolicy(dto.newPrice());
		repository.save(product);
	}
}