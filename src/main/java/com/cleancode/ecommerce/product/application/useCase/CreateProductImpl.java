package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.NewProductEvent;
import com.cleancode.ecommerce.product.application.dto.input.CreateProductDto;
import com.cleancode.ecommerce.product.application.useCase.contract.CreateProduct;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class CreateProductImpl implements CreateProduct{

	private final ProductRepository repository;
	private final EventPublisher eventPublisher;
	
	public CreateProductImpl(ProductRepository repository, EventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
	}
	
	public void execute(CreateProductDto dto) {
		Product product = dto.toProduct();
		
		eventPublisher.publish(new NewProductEvent(product.getProductId().getProductId(), product.getName().getName(), product.getProductCategory().name()));
		repository.save(product);
	}
}