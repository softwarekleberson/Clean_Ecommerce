package com.cleancode.ecommerce.customer.application.dtos.address;

import com.cleancode.ecommerce.customer.domain.customer.Charge;

public record ListChargeDto(
	
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
		String country
		
		) {
	
	public ListChargeDto(Charge charges) {
		this(charges.getPublicId(), charges.getReceiver(), charges.getStreet(),
			charges.getNumber(), charges.getNeighborhood(), charges.getZipCode(),
			charges.getObservation(), charges.getStreetType(), charges.getResidenceType(),
			charges.getCity(), charges.getState(), charges.getCountry()
			);
	}
}
