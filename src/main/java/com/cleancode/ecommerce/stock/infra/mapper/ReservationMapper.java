package com.cleancode.ecommerce.stock.infra.mapper;

import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.infra.persistence.ReservationEntity;
import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

public class ReservationMapper {

	public static Reservations toDomain (ReservationEntity entity) {
		return new Reservations(entity.getId(), entity.getCartId(), entity.getCustomerId(), entity.getQuantity());
	}
	
	public static ReservationEntity toEntity (Reservations domain, StockEntity stockEntity) {
		ReservationEntity entity = new ReservationEntity();
		entity.setId(domain.getId());
		entity.setCartId(domain.getCartId().getIdCart());
		entity.setCustomerId(domain.getCustomerId().getValue());
		entity.setQuantity(domain.getQuantity().getQuantity());
		entity.setStock(stockEntity);
		return entity;
	}
}