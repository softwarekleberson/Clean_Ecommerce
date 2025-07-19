package com.cleancode.ecommerce.customer.infra.mapper;

import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

public final class ChargeMapper {

	private ChargeMapper() {}

	public static ChargeEntity toEntity(Charge charge, CustomerEntity customerEntity) {
		ChargeEntity entity = new ChargeEntity();

		entity.setId(charge.getId().toString());
		entity.setReceiver(charge.getReceiver());
		entity.setStreet(charge.getStreet());
		entity.setNumber(charge.getNumber());
		entity.setNeighborhood(charge.getNeighborhood());
		entity.setZipCode(charge.getZipCode());
		entity.setObservation(charge.getObservation());
		entity.setStreetType(charge.getStreetType());
		entity.setTypeResidence(charge.getTypeResidence());
		entity.setCity(charge.getCity());
		entity.setState(charge.getState());
		entity.setCountry(charge.getCountry());

		entity.setCustomer(customerEntity); 

		return entity;
	}

	public static void updateEntity(Charge charge, ChargeEntity entity) {
		entity.setReceiver(charge.getReceiver());
		entity.setStreet(charge.getStreet());
		entity.setNumber(charge.getNumber());
		entity.setNeighborhood(charge.getNeighborhood());
		entity.setZipCode(charge.getZipCode());
		entity.setObservation(charge.getObservation());
		entity.setStreetType(charge.getStreetType());
		entity.setTypeResidence(charge.getTypeResidence());
		entity.setCity(charge.getCity());
		entity.setState(charge.getState());
		entity.setCountry(charge.getCountry());
	}

	public static Charge toDomain(ChargeEntity entity) {
		return new Charge(
			entity.getId(),
			entity.getReceiver(),
			entity.getStreet(),
			entity.getNumber(),
			entity.getNeighborhood(),
			entity.getZipCode(),
			entity.getObservation(),
			entity.getStreetType(),
			entity.getTypeResidence(),
			entity.getCity(),
			entity.getState(),
			entity.getCountry()
		);
	}
}