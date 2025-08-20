package com.cleancode.ecommerce.cart.domain;

import java.math.BigDecimal;

import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;

public class CartItens {

	private final CartItemId cartItemId;
    private final ProductId productId;
    private final Name productName;
    private Quantity quantity;  
    private final Price unitPrice;
    private final ReservationId reservationId;

    public CartItens(CartItemId cartItemId, ProductId productId, Name productName, Quantity quantity, Price unitPrice, ReservationId reservationId) {
        if (cartItemId == null || productId == null || productName == null || quantity == null || unitPrice == null || reservationId == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }

        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.reservationId = reservationId;
    }

    public Price calculateSubtotal() {
        BigDecimal total = this.unitPrice.getPrice()
                .multiply(BigDecimal.valueOf(this.quantity.getQuantity()));
        return new Price(total, this.unitPrice.getCoin());
    }

    public void increaseQuantity(Quantity additional) {
        if (additional.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity to add must be positive");
        }
        this.quantity = new Quantity(this.quantity.getQuantity() + additional.getQuantity());
    }

    public void changeQuantity(Quantity newQuantity) {
        if (newQuantity.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = newQuantity;
    }
    
    public CartItemId getCartItemId() {
		return cartItemId;
	}

    public ProductId getProductId() {
        return productId;
    }
    
    public String getReservationId() {
		return reservationId.getReservationId();
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
        return calculateSubtotal();
    }
}