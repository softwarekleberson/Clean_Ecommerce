package com.cleancode.ecommerce.stock.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.stock.application.service.ProductActivationService;
import com.cleancode.ecommerce.stock.application.service.ProductActivationServiceImpl;
import com.cleancode.ecommerce.stock.application.usecase.CreateProductInputImpl;
import com.cleancode.ecommerce.stock.application.usecase.CreateStock;
import com.cleancode.ecommerce.stock.application.usecase.CreateStockImpl;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

@Configuration
public class StockConfig {

	@Bean
	public CreateStock createStock (
			StockRepository stockRepository,
			ProductRepository productRepository
			) {
		
		return new CreateStockImpl(productRepository, stockRepository);
	}
	
	@Bean
	public com.cleancode.ecommerce.stock.application.usecase.CreateProductInput CreateProductInput (
			StockRepository repository,
			ProductRepository productRepository,
			ProductActivationService service
			) {
		
		return new CreateProductInputImpl(repository, productRepository, service);
	}
	
	@Bean
	public ProductActivationService productActivationService () {
		return new ProductActivationServiceImpl();
	}
}
