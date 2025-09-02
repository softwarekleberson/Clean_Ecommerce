package com.cleancode.ecommerce.product.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.ProductDeactivatedEvent;
import com.cleancode.ecommerce.product.application.dto.input.ProductStatusChangeDto;
import com.cleancode.ecommerce.product.application.useCase.contract.ManualProductDeactivation;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

public class ManualProductDeactivationImpl implements ManualProductDeactivation{

	private final ProductRepository productRepository;
	private final EventPublisher eventPublisher;
	
	public ManualProductDeactivationImpl(ProductRepository productRepository, EventPublisher eventPublisher) {
		this.productRepository = productRepository;
		this.eventPublisher = eventPublisher;
	}
	
	@Override
	public void execute(String productId, ProductStatusChangeDto dto) {
		Product product = productRepository.findById(productId)
	    .orElseThrow(() -> new IllegalDomainException("Product with id : " + productId + "not found"));
		product.productStatusPolicyManualDeactivation(dto.justification(), dto.category());
		
		eventPublisher.publish(new ProductDeactivatedEvent(productId, product.isActive()));
		productRepository.save(product);
	}
}