package com.cleancode.ecommerce.customer.infra.mapper;

import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.DeliveryEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

public final class DeliveryMapper {

	private DeliveryMapper() {
	}

	public static DeliveryEntity toEntity(Delivery delivery, CustomerEntity customerEntity) {
		DeliveryEntity entity = new DeliveryEntity();

		entity.setId(delivery.getId());
		entity.setReceiver(delivery.getReceiver());
		entity.setStreet(delivery.getStreet());
		entity.setNumber(delivery.getNumber());
		entity.setNeighborhood(delivery.getNeighborhood());
		entity.setZipCode(delivery.getZipCode());
		entity.setObservation(delivery.getObservation());
		entity.setStreetType(delivery.getStreetType());
		entity.setTypeResidence(delivery.getTypeResidence());
		entity.setCity(delivery.getCity());
		entity.setState(delivery.getState());
		entity.setCountry(delivery.getCountry());
		entity.setDeliveryPhrase(delivery.getDeliveryPhrase());

		entity.setCustomer(customerEntity);

		return entity;
	}


	public static Delivery toDomain(DeliveryEntity entity) {
		return new Delivery(entity.getDeliveryPhrase(), entity.getReceiver(), entity.getStreet(), entity.getNumber(),
				entity.getNeighborhood(), entity.getZipCode(), entity.getObservation(), entity.getStreet(),
				entity.getTypeResidence(), entity.getCity(), entity.getState(), entity.getCountry());
	}
}