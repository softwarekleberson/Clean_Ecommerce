package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.event.ProductDeactivatedEvent;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.shared.event.product.event.EventPublisher;

public class DeactivateProductImpl implements DeactivateProduct{

	private final ProductRepository productRepository;
	private final EventPublisher eventPublisher;
	
	public DeactivateProductImpl(ProductRepository productRepository, EventPublisher eventPublisher) {
		this.productRepository = productRepository;
		this.eventPublisher = eventPublisher;
	}
	
	@Override
	public void execute(String productId) {
		Product product = productRepository.listProduct(productId)
	    .orElseThrow(() -> new IllegalDomainException("Product with id : " + productId + "not found"));
	
		product.deactivate();
		eventPublisher.publish(new ProductDeactivatedEvent(productId));
		
		productRepository.create(product);
	}
}