package com.cleancode.ecommerce.order.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.order.application.service.ValidateProductHasStock;
import com.cleancode.ecommerce.order.application.service.ValidateProductHasStockServiceImpl;
import com.cleancode.ecommerce.order.application.useCase.AddProductToCartImpl;
import com.cleancode.ecommerce.order.application.useCase.contract.AddProductToCart;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

@Configuration
public class CartConfig {

	@Bean
	public AddProductToCart addProductToCart (
			
			CustomerRepository customerRepository,
			ProductRepository productRepository,
			StockRepository stockRepository,
			CartRepository cartRepository,
			ValidateProductHasStock validateProduct
			) {
		
		return new AddProductToCartImpl(customerRepository, productRepository, stockRepository, cartRepository, validateProduct);
	}
	
	@Bean
	public ValidateProductHasStock validateProductHasStock () {
		return new ValidateProductHasStockServiceImpl();
	}
}