package com.cleancode.ecommerce.customer.application.dtos.address;

import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Delivery;

import jakarta.validation.constraints.NotBlank;

public class CreateDeliveryDto {

	private String id;
	
	@NotBlank
	private String receiver;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String neighborhood;
	
	@NotBlank
	private String zipCode;
	
	@NotBlank
	private String observation;
	
	@NotBlank
	private String streetType;
	
	@NotBlank
	private String typeResidence;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String deliveryPhrase;

	public CreateDeliveryDto(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country,
			String deliveryPhrase) {

		this.id = UUID.randomUUID().toString();
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
		return new Delivery(id,deliveryPhrase, receiver, street, number, neighborhood, zipCode, observation, streetType,
				typeResidence, city, state, country);
	}
}
