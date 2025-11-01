package com.cleancode.ecommerce.stock.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cleancode.ecommerce.stock.domain.ProductInput;
import com.cleancode.ecommerce.stock.domain.ProductQuality;

public record ListInputProductDto(

		int quantity,
		ProductQuality productQuality,
		LocalDateTime entryTime,
		BigDecimal purchasePrice,
		String supplier

) {

	public ListInputProductDto(ProductInput input) {
		this(input.getQuantity().getQuantity(),
			input.getProductQuality(),
			input.getEntryTime(),
			input.getPurchasePrice().getPrice(),
			input.getSupplier().getSupplier());
	}
}