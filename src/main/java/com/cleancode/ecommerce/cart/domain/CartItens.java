package com.cleancode.ecommerce.cart.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.stock.domain.Quantity;

public class CartItens {

	private final ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price unitPrice;
	private Price subtotal;

	public CartItens(ProductId productId, Name productName, Quantity quantity, Price unitPrice) {
		
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		
		subtotal();
	}

	public Price subtotal() {
		BigDecimal total = this.unitPrice.getPrice().multiply(BigDecimal.valueOf(this.quantity.getQuantity()));
		this.subtotal = new Price(total, this.unitPrice.getCoin());
		return this.subtotal;
	}

	public ProductId getProductId() {
		return productId;
	}

	public Name getProductName() {
		return productName;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public Price getUnitPrice() {
		return unitPrice;
	}

	public Price getSubtotal() {
		return subtotal;
	}
}