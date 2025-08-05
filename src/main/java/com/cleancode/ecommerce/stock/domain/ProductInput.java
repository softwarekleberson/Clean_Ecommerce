package com.cleancode.ecommerce.stock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductInput {

	private final Quantity quantity;
	private final ProductQuality productQuality;
	private final LocalDateTime entryTime;
	private final Price purchasePrice;
	private final Supplier supplier;

	public ProductInput(int quantity, ProductQuality productQuality, BigDecimal purchasePrice, String supplier) {
		this.quantity = new Quantity(quantity);
		this.productQuality = productQuality;
		this.purchasePrice = new Price(purchasePrice);
		this.supplier = new Supplier(supplier);
		this.entryTime = LocalDateTime.now();
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public ProductQuality getProductQuality() {
		return productQuality;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	
	public Price getPurchasePrice() {
		return purchasePrice;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entryTime, productQuality, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInput other = (ProductInput) obj;
		return Objects.equals(entryTime, other.entryTime) && productQuality == other.productQuality
				&& quantity == other.quantity;
	}
}
