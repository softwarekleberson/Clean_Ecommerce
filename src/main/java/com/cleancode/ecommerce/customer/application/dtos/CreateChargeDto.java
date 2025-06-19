package com.cleancode.ecommerce.customer.application.dtos;

import com.cleancode.ecommerce.customer.domain.Charge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateChargeDto {

	@NotBlank(message = "Receiver is required")
	private String receiver;

	@NotBlank(message = "Street is required")
	private String street;

	@NotBlank(message = "Number is required")
	private String number;

	@NotBlank(message = "Neighborhood is required")
	private String neighborhood;

	@NotBlank(message = "Zip code is required")
	@Pattern(regexp = "\\d{8}", message = "Zip code must contain 8 digits")
	private String zipCode;

	@Size(max = 255, message = "Observation must be at most 255 characters")
	private String observation;

	@NotBlank(message = "Street type is required")
	private String streetType;

	@NotBlank(message = "Type of residence is required")
	private String typeResidence;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Country is required")
	private String country;

	public CreateChargeDto(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {

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
	}

	public Charge createCharge() {
		return new Charge(receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city,
				state, country);
	}
}
