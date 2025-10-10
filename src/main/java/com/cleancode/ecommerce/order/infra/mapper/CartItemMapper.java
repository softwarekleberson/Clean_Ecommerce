package com.cleancode.ecommerce.order.infra.mapper;

import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.CartItens;
import com.cleancode.ecommerce.order.domain.cart.UrlProduct;
import com.cleancode.ecommerce.order.infra.persistence.CartEntity;
import com.cleancode.ecommerce.order.infra.persistence.CartItemEntity;
import com.cleancode.ecommerce.order.infra.persistence.TypeCoinEntity;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;

public class CartItemMapper {

	public static CartItemEntity toEntity(CartItens item, CartEntity cartEntity) {
		CartItemEntity entity = new CartItemEntity();
		entity.setCart_item_id(item.getCartItemId().getCartItemId());
		entity.setCart(cartEntity);
		entity.setProduct_id(item.getProductId().getProductId());
		entity.setProduct_name(item.getProductName().getName());
		entity.setUrl_product(item.getUrlProduct().getUrlProduct());
		entity.setQuantity(item.getQuantity().getQuantity());
		entity.setUnit_price(item.getUnitPrice().getPrice());
		entity.setCoin(TypeCoinEntity.valueOf(item.getUnitPrice().getCoin().name()));
		entity.setSubtotal(item.getSubtotal().getPrice());
		entity.setReservation_id(item.getReservationId());
		return entity;
	}

	public static CartItens toDomain(CartItemEntity entity) {
		return new CartItens(new CartItemId(entity.getCart_item_id()), new ProductId(entity.getProduct_id()),
				new Name(entity.getProduct_name()), new UrlProduct(entity.getUrl_product()) ,new Quantity(entity.getQuantity()),
				new Price(entity.getUnit_price(), TypeCoin.valueOf(entity.getCoin().name())),
				new ReservationId(entity.getReservation_id()));
	}
}