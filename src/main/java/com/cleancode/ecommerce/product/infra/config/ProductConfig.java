package com.cleancode.ecommerce.product.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.product.application.useCase.CreateProduct;
import com.cleancode.ecommerce.product.application.useCase.CreateProductImpl;
import com.cleancode.ecommerce.product.application.useCase.ListAllProduct;
import com.cleancode.ecommerce.product.application.useCase.ListAllProductImpl;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;

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
}