package com.cleancode.ecommerce.customer.application.dtos;

import com.cleancode.ecommerce.customer.domain.customer.Delivery;

public class CreateDeliveryDto {

	private String receiver;
	private String street;
	private String number;
	private String neighborhood;
	private String zipCode;
	private String observation;
	private String streetType;
	private String typeResidence;
	private String city;
	private String state;
	private String country;
	private String deliveryPhrase;

	public CreateDeliveryDto(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country,
			String deliveryPhrase) {

		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.zipCode = zipCode;
		this.observation = observation;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
		this.state = state;
		this.country = country;
		this.deliveryPhrase = deliveryPhrase;
	}

	public Delivery createDelivery() {
		return new Delivery(deliveryPhrase, receiver, street, number, neighborhood, zipCode, observation, streetType,
				typeResidence, city, state, country);
	}

}
