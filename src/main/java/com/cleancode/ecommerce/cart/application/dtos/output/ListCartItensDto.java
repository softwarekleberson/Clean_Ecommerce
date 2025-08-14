package com.cleancode.ecommerce.cart.application.dtos.output;

import java.math.BigDecimal;

import com.cleancode.ecommerce.cart.domain.CartItens;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public record ListCartItensDto(

		String productId,
		String productName,
		int quantity,
		BigDecimal unitPrice,
		BigDecimal subtotal,
		TypeCoin coin
		
							) {

	public ListCartItensDto(CartItens itens) {
		this(itens.getProductId().getProductId(),
			 itens.getProductName().getName(),
			 itens.getQuantity().getQuantity(),
			 itens.getUnitPrice().getPrice(),
			 itens.getSubtotal().getPrice(),
			 itens.getSubtotal().getCoin());
	}
}