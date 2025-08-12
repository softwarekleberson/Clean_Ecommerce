package com.cleancode.ecommerce.stock.infra.mapper;

import com.cleancode.ecommerce.stock.domain.Reservations;
import com.cleancode.ecommerce.stock.infra.persistence.ReservationEntity;
import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

public class ReservationMapper {

	public static Reservations toDomain (ReservationEntity entity) {
		return new Reservations(entity.getReservation_id(), entity.getCart_id(), entity.getCustomer_id(), entity.getQuantity());
	}
	
	public static ReservationEntity toEntity (Reservations domain, StockEntity stockEntity) {
		ReservationEntity entity = new ReservationEntity();
		entity.setReservation_id(domain.getId());
		entity.setCart_id(domain.getCartId().getCartId());
		entity.setCustomer_id(domain.getCustomerId().getValue());
		entity.setQuantity(domain.getQuantity().getQuantity());
		entity.setStock(stockEntity);
		return entity;
	}
}