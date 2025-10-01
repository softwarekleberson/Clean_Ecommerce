package com.cleancode.ecommerce.order.application.useCase;

import com.cleancode.ecommerce.order.application.dtos.input.UpdateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.service.CancelProductStockReservation;
import com.cleancode.ecommerce.order.application.service.UpdateNewReservation;
import com.cleancode.ecommerce.order.application.useCase.contract.UpdateCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

public class UpdateCartImpl implements UpdateCart {

	private final CartRepository cartRepository;
	private final StockRepository stockRepository;
	private final CancelProductStockReservation service;
	private final UpdateNewReservation updateNewReservation;

	public UpdateCartImpl(CartRepository cartRepository, StockRepository stockRepository, CancelProductStockReservation service, UpdateNewReservation updateNewReservation) {
		this.cartRepository = cartRepository;
		this.stockRepository = stockRepository;
		this.service = service;
		this.updateNewReservation = updateNewReservation;
	}

	@Override
	public CartDto execute(String customerId, UpdateCartDto dto) {
		Cart cart = cartRepository.getCartCustomer(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer with id : " + customerId + " not found"));

		var stock = stockRepository.findStockByReservationId(dto.reservationId()).orElseThrow(() -> new IllegalArgumentException ("Reservation id not found " + dto.reservationId()));
		var stockAfterCancel = service.cancelReservationAfterDelete(stock, dto.reservationId());
		var stockAfterReservation = updateNewReservation.creteNewReservation(stockAfterCancel, dto.quantity(), customerId, customerId);
		
		cart.changeProductQuantity(new CartItemId(dto.cartItemId()), new Quantity(dto.quantity()));		
				
		cartRepository.save(cart);
		stockRepository.save(stockAfterReservation);
		return new CartDto(cart);
	}
}