package com.cleancode.ecommerce.stock.domain;

import java.util.Objects;

import com.cleancode.ecommerce.product.domain.IdProduct;

public class ProductOutput {

	private final OrderId orderId;
	private final IdProduct productId;
	private final int quantity;

	public ProductOutput(OrderId orderId, IdProduct productId, int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public OrderId getOrderId() {
		return orderId;
	}
	
	public IdProduct getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOutput other = (ProductOutput) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId)
				&& quantity == other.quantity;
	}
}