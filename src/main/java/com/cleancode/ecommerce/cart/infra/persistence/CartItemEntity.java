package com.cleancode.ecommerce.cart.infra.persistence;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cart_item")
@Entity
public class CartItemEntity {

	@Id
	private String cart_item = UUID.randomUUID().toString();
	
	private String product_id;
	private String product_name;
	private int quantity;
	private BigDecimal unit_price;
	private TypeCoinEntity subtotal;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartEntity cart;

	@Override
	public int hashCode() {
		return Objects.hash(cart, cart_item, product_id, product_name, quantity, subtotal, unit_price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItemEntity other = (CartItemEntity) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(cart_item, other.cart_item)
				&& Objects.equals(product_id, other.product_id) && Objects.equals(product_name, other.product_name)
				&& quantity == other.quantity && subtotal == other.subtotal
				&& Objects.equals(unit_price, other.unit_price);
	}
}
