package com.cleancode.ecommerce.product.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.product.application.useCase.ManualProductActivation;
import com.cleancode.ecommerce.product.application.useCase.ManualProductActivationImpl;
import com.cleancode.ecommerce.product.application.useCase.CreateProduct;
import com.cleancode.ecommerce.product.application.useCase.CreateProductImpl;
import com.cleancode.ecommerce.product.application.useCase.IncreaseSellingPriceAboveProfitMargin;
import com.cleancode.ecommerce.product.application.useCase.IncreaseSellingPriceAboveProfitMarginImpl;
import com.cleancode.ecommerce.product.application.useCase.ManualProductDeactivation;
import com.cleancode.ecommerce.product.application.useCase.ManualProductDeactivationImpl;
import com.cleancode.ecommerce.product.application.useCase.ListAllProduct;
import com.cleancode.ecommerce.product.application.useCase.ListAllProductImpl;
import com.cleancode.ecommerce.product.application.useCase.ListProduct;
import com.cleancode.ecommerce.product.application.useCase.ListProductImpl;
import com.cleancode.ecommerce.product.application.useCase.ReviseDetails;
import com.cleancode.ecommerce.product.application.useCase.ReviseDetailsImpl;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

@Configuration
public class ProductConfig {

	@Bean
	public CreateProduct createProduct(ProductRepository productRepository, EventPublisher eventPublisher) {
		return new CreateProductImpl(productRepository, eventPublisher);
	}
	
	@Bean
	public ReviseDetails reviseDetails(ProductRepository productRepository) {
		return new ReviseDetailsImpl(productRepository);
	}
	
	@Bean
	public IncreaseSellingPriceAboveProfitMargin increaseSelling (ProductRepository productRepository) {
		return new IncreaseSellingPriceAboveProfitMarginImpl(productRepository);
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
	public ManualProductDeactivation deactivateProduct (ProductRepository productRepository, EventPublisher eventPublisher) {
		return new ManualProductDeactivationImpl(productRepository, eventPublisher);
	}
	
	@Bean
	public ManualProductActivation activateManually (ProductRepository productRepository) {
		return new ManualProductActivationImpl(productRepository);
	}
}