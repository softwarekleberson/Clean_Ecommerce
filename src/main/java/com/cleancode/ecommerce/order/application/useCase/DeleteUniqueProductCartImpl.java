package com.cleancode.ecommerce.order.application.useCase;

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

	public DeleteUniqueProductCartImpl(CartRepository cartRepository, StockRepository stockRepository, CancelProductStockReservation service) {
		this.cartRepository = cartRepository;
		this.stockRepository = stockRepository;
		this.service = service;
	}

	@Override
	public void execute(String customerId, DeleteUniqueProductToCartDto dto) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));
		
		cart.removeProductFromCart(new CartItemId(dto.cartItemId()));
				
		var stock = stockRepository.findStockByReservationId(dto.reservationId()).orElseThrow(() -> new IllegalArgumentException ("Reservation id not found " + dto.reservationId()));
		var stockAfterCancel = service.cancelReservationAfterDelete(stock, dto.reservationId());
		
	    stockRepository.save(stockAfterCancel);
	    cartRepository.save(cart);
	}
}