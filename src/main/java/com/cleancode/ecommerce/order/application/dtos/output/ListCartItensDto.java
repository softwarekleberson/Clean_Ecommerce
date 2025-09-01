package com.cleancode.ecommerce.order.application.dtos.output;

import java.math.BigDecimal;

import com.cleancode.ecommerce.order.domain.cart.CartItens;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public record ListCartItensDto(

		String reservationId,
		String cartItemId,
		String productId,
		String productName,
		int quantity,
		BigDecimal unitPrice,
		BigDecimal subtotal,
		TypeCoin coin
		
							) {

	public ListCartItensDto(CartItens itens) {
		this(itens.getReservationId(),
			 itens.getCartItemId().getCartItemId(),
			 itens.getProductId().getProductId(),
			 itens.getProductName().getName(),
			 itens.getQuantity().getQuantity(),
			 itens.getUnitPrice().getPrice(),
			 itens.getSubtotal().getPrice(),
			 itens.getSubtotal().getCoin());
	}
}