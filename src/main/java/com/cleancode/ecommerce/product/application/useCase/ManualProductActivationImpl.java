package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.application.dto.input.ProductStatusChangeDto;
import com.cleancode.ecommerce.product.application.useCase.contract.ManualProductActivation;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ManualProductActivationImpl implements ManualProductActivation {

	private final ProductRepository productRepository;

	public ManualProductActivationImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void execute(String productId, ProductStatusChangeDto dto) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalDomainException("Product with id : " + productId + "not found"));
		product.productStatusPolicyActivation(dto.justification(), dto.category());

		productRepository.save(product);
	}
}