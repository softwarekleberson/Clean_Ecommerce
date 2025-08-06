package com.cleancode.ecommerce.product.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.product.application.useCase.CreateProduct;
import com.cleancode.ecommerce.product.application.useCase.CreateProductImpl;
import com.cleancode.ecommerce.product.application.useCase.DeactivateProduct;
import com.cleancode.ecommerce.product.application.useCase.DeactivateProductImpl;
import com.cleancode.ecommerce.product.application.useCase.ListAllProduct;
import com.cleancode.ecommerce.product.application.useCase.ListAllProductImpl;
import com.cleancode.ecommerce.product.application.useCase.ListProduct;
import com.cleancode.ecommerce.product.application.useCase.ListProductImpl;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.shared.event.product.event.EventPublisher;

@Configuration
public class ProductConfig {

	@Bean
	public CreateProduct createProduct(ProductRepository productRepository) {
		return new CreateProductImpl(productRepository);
	}

	@Bean
	public ListAllProduct listAllProduct(ProductRepository productRepository) {
		return new ListAllProductImpl(productRepository);
	}
	
	@Bean
	public ListProduct listProduct(ProductRepository productRepository) {
		return new ListProductImpl(productRepository);
	}
	
	@Bean
	public DeactivateProduct deactivateProduct (ProductRepository productRepository, EventPublisher eventPublisher) {
		return new DeactivateProductImpl(productRepository, eventPublisher);
	}
}