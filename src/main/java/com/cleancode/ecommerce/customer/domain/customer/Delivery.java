package com.cleancode.ecommerce.customer.domain.customer;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Delivery extends Address{

	private String deliveryPhrase; 
	
	public Delivery(String id, String deliveryPhrase, String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {
		super(id, receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city, state, country);
		
		if(isValid(deliveryPhrase) || inputSize(deliveryPhrase)) throw new IllegalDomainException("Delivery Phrase is requerid");
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public void update(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country, String deliveryPhrase) {

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
		if (observation != null && !observation.isBlank() && inputSize(observation))
			this.observation = observation;
		if (streetType != null && !streetType.isBlank())
			this.streetType = streetType;
		if (typeResidence != null && !typeResidence.isBlank())
			this.typeResidence = typeResidence;
		if (city != null && !city.isBlank())
			this.city = city;
		if (state != null && !state.isBlank())
			this.state = state;
		if (country != null && !country.isBlank())
			this.country = country;
		if (deliveryPhrase != null && !deliveryPhrase.isBlank() && inputSize(deliveryPhrase))
			this.deliveryPhrase = deliveryPhrase;
	}

	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(deliveryPhrase);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(deliveryPhrase, other.deliveryPhrase);
	}
}