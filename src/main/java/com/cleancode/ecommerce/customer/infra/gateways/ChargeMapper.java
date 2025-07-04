package com.cleancode.ecommerce.customer.infra.gateways;

import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;

public class ChargeMapper {

	public ChargeMapper() {
	}

	public static ChargeEntity toEntity(Charge charge) {
		ChargeEntity entity = new ChargeEntity();

		entity.setId(charge.getId());
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

		return entity;
	}

	public static Charge toDomain(ChargeEntity entity) {
		return new Charge(entity.getReceiver(), entity.getStreet(), entity.getNumber(), entity.getNeighborhood(),
				entity.getZipCode(), entity.getObservation(), entity.getStreet(), entity.getTypeResidence(),
				entity.getCity(), entity.getState(), entity.getCountry());
	}
}
