package com.cleancode.ecommerce.cart.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;

public class Cart {

	private final CartId cartId;
	private final CustomerId customerId;
	private List<CartItens> cartItens = new ArrayList<>();
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Price totalPrice;

	public Cart(CustomerId customerId) {

		if (customerId == null)
			throw new IllegalDomainException("Customer ID cannot be null");

		this.cartId = new CartId();
		this.customerId = customerId;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	private void recalculateTotalPrice() {
		BigDecimal total = cartItens.stream()
		.map(item -> item.getSubtotal().getPrice())
		.reduce(BigDecimal.ZERO,
		BigDecimal::add);

		TypeCoin coin = cartItens.isEmpty() ? TypeCoin.DOLAR : cartItens.get(0).getUnitPrice().getCoin();
		this.totalPrice = new Price(total, coin);
	}

	public void addProductToCart(String productId, String productName, int quantity, BigDecimal unitPrice, TypeCoin coin) {

		this.cartItens.add(new CartItens(new ProductId(productId), new Name(productName), new Quantity(quantity), new Price(unitPrice, coin)));
		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void updateQuantityProduct(String productIdToRemove, ProductId productId, Name productName,
			Quantity quantity, Price unitPrice) {
		if (productIdToRemove == null || productIdToRemove.isBlank() || this.cartItens == null) {
			throw new IllegalDomainException(
					"Cannot remove product this cart: id is null/empty or cart item list is not initialized");
		}

		this.cartItens.removeIf(c -> c.getProductId().getProductId().equals(productIdToRemove));
		this.cartItens.add(new CartItens(productId, productName, quantity, unitPrice));
		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void removeAllProductToCart() {
		this.cartItens.clear();
		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void removeProductToCart(String productId) {
		if (productId == null || productId.isBlank() || this.cartItens.isEmpty()) {
			throw new IllegalDomainException(
					"Cannot remove product this cart: id is null/empty or cart item list is not initialized");
		}

		boolean removed = this.cartItens.removeIf(c -> productId.equals(c.getProductId().getProductId()));

		if (!removed)
			throw new IllegalDomainException("Product not found in cart");

		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public CartId getCartId() {
		return cartId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public List<CartItens> getCartItens() {
		return Collections.unmodifiableList(this.cartItens);
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Price getTotalPrice() {
		return totalPrice;
	}
}