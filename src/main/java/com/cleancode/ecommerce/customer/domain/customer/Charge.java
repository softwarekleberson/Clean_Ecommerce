package com.cleancode.ecommerce.customer.domain.customer;

public class Charge extends Address {

	public Charge(String id, String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {
		super(id, receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city, state,
				country);
	}

	public void update(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {

		if (receiver != null && !receiver.isBlank())
			this.receiver = receiver;
		if (street != null && !street.isBlank())
			this.street = street;
		if (number != null && !number.isBlank())
			this.number = number;
		if (neighborhood != null && !neighborhood.isBlank())
			this.neighborhood = neighborhood;
		if (zipCode != null && !zipCode.isBlank() && isZipCode(zipCode))
			this.zipCode = zipCode;
		if (observation != null)
			this.observation = observation;
		if (streetType != null && !streetType.isBlank())
			this.streetType = streetType;
		if (typeResidence != null && !typeResidence.isBlank())
			this.residenceType = typeResidence;
		if (city != null && !city.isBlank())
			this.city = city;
		if (state != null && !state.isBlank())
			this.state = state;
		if (country != null && !country.isBlank())
			this.country = country;
	}
}