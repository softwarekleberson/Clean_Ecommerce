package com.cleancode.ecommerce.customer.application.dtos.address;

import com.cleancode.ecommerce.customer.domain.customer.Delivery;

public record ListDeliveryDto(
	
		String id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String zipCode,
		String observation,
		String streetType,
		String typeResidence,
		String city,
		String state,
		String country,
		String deliveryPhrase
		
		) {
	
	public ListDeliveryDto(Delivery delivery) {
		this(delivery.getId(), delivery.getReceiver(), delivery.getStreet(),
			delivery.getNumber(), delivery.getNeighborhood(), delivery.getZipCode(),
			delivery.getObservation(), delivery.getStreetType(), delivery.getTypeResidence(),
			delivery.getCity(), delivery.getState(), delivery.getCountry(),
			delivery.getDeliveryPhrase());
	}
}
