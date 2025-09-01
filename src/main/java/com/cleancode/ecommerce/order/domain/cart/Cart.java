package com.cleancode.ecommerce.cart.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;

public class Cart {

	private final CartId cartId;
	private final CustomerId customerId;
	private final List<CartItens> cartItens;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Price totalPrice;

	public Cart(CartId id, CustomerId customerId) {
		if (customerId == null) {
			throw new IllegalDomainException("Customer ID cannot be null");
		}

		this.cartId = id;
		this.customerId = customerId;
		this.cartItens = new ArrayList<>();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		recalculateTotalPrice();
	}

	public Cart(CartId cartId, CustomerId customerId, List<CartItens> cartItens, LocalDateTime createdAt,
			LocalDateTime updatedAt) {

		if (cartId == null || customerId == null) {
			throw new IllegalDomainException("Cart ID and Customer ID cannot be null");
		}

		this.cartId = cartId;
		this.customerId = customerId;
		this.cartItens = new ArrayList<>(cartItens != null ? cartItens : new ArrayList<>());
		this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
		this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
		recalculateTotalPrice();
	}

	private void recalculateTotalPrice() {
		BigDecimal total = cartItens.stream().map(item -> item.getSubtotal().getPrice()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		TypeCoin coin = cartItens.isEmpty() ? TypeCoin.DOLAR : cartItens.get(0).getUnitPrice().getCoin();

		this.totalPrice = new Price(total, coin);
	}

	public void addProductToCart(CartItemId cartItemId, ProductId productId, Name name, Quantity quantity,
			Price unitPrice, ReservationId reservationId) {

		if (productId == null || name == null || quantity == null || unitPrice == null) {
			throw new IllegalDomainException("Product data cannot be null");
		}

		Optional.ofNullable(findItemByCartItem(cartItemId))
		.ifPresentOrElse(item -> item.increaseQuantity(quantity),
		 () -> cartItens.add(new CartItens(cartItemId, productId, name, quantity, unitPrice, reservationId)));

		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void changeProductQuantity(CartItemId cartItemId, Quantity newQuantity) {
		if (cartItemId == null || newQuantity == null) {
			throw new IllegalDomainException("Product ID and quantity cannot be null");
		}

		CartItens item = findItemByCartItem(cartItemId);

		if (item == null) {
			throw new IllegalDomainException("Product not found in cart");
		}

		item.changeQuantity(newQuantity);
		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void removeAllProducts() {
		cartItens.clear();
		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	public void removeProductFromCart(ProductId productId) {
		if (productId == null) {
			throw new IllegalDomainException("Product ID cannot be null");
		}

		boolean removed = cartItens.removeIf(c -> c.getProductId().equals(productId));

		if (!removed) {
			throw new IllegalDomainException("Product not found in cart");
		}

		recalculateTotalPrice();
		this.updatedAt = LocalDateTime.now();
	}

	private CartItens findItemByCartItem(CartItemId cartItemId) {
		return cartItens.stream().filter(c -> c.getCartItemId().equals(cartItemId)).findFirst().orElse(null);
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

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", cartItens=" + cartItens + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", totalPrice=" + totalPrice + "]";
	}
}