package com.cleancode.ecommerce.order.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.order.application.service.CancelProductStockReservation;
import com.cleancode.ecommerce.order.application.service.CancelProductStockReservationImpl;
import com.cleancode.ecommerce.order.application.service.ValidateProductHasStock;
import com.cleancode.ecommerce.order.application.service.ValidateProductHasStockServiceImpl;
import com.cleancode.ecommerce.order.application.useCase.AddProductToCartImpl;
import com.cleancode.ecommerce.order.application.useCase.DeleteAllCartImpl;
import com.cleancode.ecommerce.order.application.useCase.DeleteUniqueProductCartImpl;
import com.cleancode.ecommerce.order.application.useCase.ListCartImpl;
import com.cleancode.ecommerce.order.application.useCase.UpdateCartImpl;
import com.cleancode.ecommerce.order.application.useCase.contract.AddProductToCart;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteAllCart;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteUniqueProductCart;
import com.cleancode.ecommerce.order.application.useCase.contract.ListCart;
import com.cleancode.ecommerce.order.application.useCase.contract.UpdateCart;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

@Configuration
public class CartConfig {

	@Bean
	public AddProductToCart addProductToCart(

			CustomerRepository customerRepository, ProductRepository productRepository, StockRepository stockRepository,
			CartRepository cartRepository, ValidateProductHasStock validateProduct) {

		return new AddProductToCartImpl(customerRepository, productRepository, stockRepository, cartRepository,
				validateProduct);
	}

	@Bean
	public ListCart listCart(CartRepository cartRepository) {
		return new ListCartImpl(cartRepository);
	}
	
	@Bean
	public UpdateCart updateCart (CartRepository cartRepository) {
		return new UpdateCartImpl(cartRepository);
	}
	
	@Bean
	public DeleteAllCart deleteAllCart (CartRepository cartRepository) {
		return new DeleteAllCartImpl(cartRepository);
	}
	
	@Bean
	public DeleteUniqueProductCart deleteUniqueProductCart (CartRepository cartRepository, StockRepository stockRepository, CancelProductStockReservation service) {
		return new DeleteUniqueProductCartImpl(cartRepository, stockRepository, service);
	}

	@Bean
	public CancelProductStockReservation cancelProductStockReservation () {
		return new CancelProductStockReservationImpl();
	}
	
	@Bean
	public ValidateProductHasStock validateProductHasStock() {
		return new ValidateProductHasStockServiceImpl();
	}
}