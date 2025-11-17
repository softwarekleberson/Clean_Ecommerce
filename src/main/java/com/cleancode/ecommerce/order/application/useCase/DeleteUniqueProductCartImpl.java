package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.order.application.dtos.input.DeleteUniqueProductToCartDto;
import com.cleancode.ecommerce.order.application.service.CancelProductStockReservation;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteUniqueProductCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

public class DeleteUniqueProductCartImpl implements DeleteUniqueProductCart {

	private final CartRepository cartRepository;
	private final StockRepository stockRepository;
	private final CancelProductStockReservation service;
	private final CustomerRepository customerRepository;
	
	public DeleteUniqueProductCartImpl(CartRepository cartRepository, StockRepository stockRepository, CancelProductStockReservation service, CustomerRepository customerRepository) {
		this.cartRepository = cartRepository;
		this.stockRepository = stockRepository;
		this.service = service;
		this.customerRepository = customerRepository;
	}

	@Override
	public void execute(String email, DeleteUniqueProductToCartDto dto) {
		
		Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new IllegalDomainException("Customer not found"));

		
		Cart cart = cartRepository.getCartCustomer(customer.getId().getValue())
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customer.getId().getValue() + " not found"));
		
		cart.removeProductFromCart(new CartItemId(dto.cartItemId()));
				
		var stock = stockRepository.findStockByReservationId(dto.reservationId()).orElseThrow(() -> new IllegalArgumentException ("Reservation id not found " + dto.reservationId()));
		var stockAfterCancel = service.cancelReservationAfterDelete(stock, dto.reservationId());
		
	    stockRepository.save(stockAfterCancel);
	    cartRepository.save(cart);
	}
}